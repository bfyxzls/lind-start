package com.lind.start.test;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class TestApplication {

	public static void main(String[] args) {
		// 代理可以为null
		// http://139.198.16.175:30000/api/kit/openai-proxy
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 10809));
		OpenAiClient openAiClient = OpenAiClient.builder().apiKey("sk-**************").proxy(proxy).build();
		// 简单模型
		// CompletionResponse completions =
		// //openAiClient.completions("我想申请转专业，从计算机专业转到会计学专业，帮我完成一份两百字左右的申请书");
		// 最新GPT-3.5-Turbo模型
		Message message = Message.builder().role(Message.Role.USER).content("你好啊我的伙伴！").build();
		ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
		ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
		chatCompletionResponse.getChoices().forEach(e -> {
			System.out.println(e.getMessage());
		});

		// SpringApplication.run(TestApplication.class, args);
	}

	@Scheduled(cron = "0/1 * * * * ?")
	public void saveFile() throws InterruptedException {

		System.out.println("start" + Thread.currentThread().getId());
		Thread.sleep(100);
		System.out.println("end" + Thread.currentThread().getId());

	}

}
