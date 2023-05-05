package com.lind.lindmanager.controller;

import com.lind.lindmanager.util.SseEmitterUtil;
import com.lzhpo.chatgpt.OpenAiClient;
import com.lzhpo.chatgpt.entity.chat.ChatCompletionRequest;
import com.lzhpo.chatgpt.sse.SseEventSourceListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EnableScheduling
@RestController
@RequestMapping("/sse")
public class SseController {

	private static Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

	@Autowired
	OpenAiClient openAiClient;

	// 测试实时推送：/f1/sse-test
	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> flux() {
		return Flux.create(sink -> {
			new Thread(() -> {
				String[] arr = { "pq1", "pq2", "pq3" };
				for (int i = 0; i < 3; i++) {
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) {
					}
					sink.next(arr[i]);
					if (i == 2) {
						sink.complete();
					}
				}
			}).start();
		});
	}


	@GetMapping("/chat-do")
	public SseEmitter sseStreamChat(@RequestParam String message) {
		SseEmitter sseEmitter = new SseEmitter();
		ChatCompletionRequest request = ChatCompletionRequest.create(message);
		openAiClient.streamChatCompletions(request, new SseEventSourceListener(sseEmitter));
		return sseEmitter;
	}


	/**
	 * 用于创建连接
	 */
	@GetMapping("/connect/{userId}")
	public SseEmitter connect(@PathVariable String userId) {
		return SseEmitterUtil.connect(userId);
	}

	/**
	 * 推送给所有人
	 * @param message
	 * @return
	 */
	@GetMapping("/push/{message}")
	public ResponseEntity<String> push(@PathVariable(name = "message") String message) {
		// 获取连接人数
		int userCount = SseEmitterUtil.getUserCount();
		// 如果无在线人数，返回
		if (userCount < 1) {
			return ResponseEntity.status(500).body("无人在线！");
		}
		SseEmitterUtil.batchSendMessage(message);
		return ResponseEntity.ok("发送成功！");
	}

	/**
	 * 发送给单个人
	 * @param message
	 * @param userid
	 * @return
	 */
	@GetMapping("/push_one/{messsage}/{userid}")
	public ResponseEntity<String> pushOne(@PathVariable(name = "message") String message,
			@PathVariable(name = "userid") String userid) {
		SseEmitterUtil.sendMessage(userid, message);
		return ResponseEntity.ok("推送消息给" + userid);
	}

	/**
	 * 关闭连接
	 */
	@GetMapping("/close/{userid}")
	public ResponseEntity<String> close(@PathVariable("userid") String userid) {
		SseEmitterUtil.removeUser(userid);
		return ResponseEntity.ok("连接关闭");
	}

}
