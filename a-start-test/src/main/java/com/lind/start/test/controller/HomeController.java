package com.lind.start.test.controller;

import com.lind.common.event.EventBusService;
import com.lind.lock.annotation.RepeatSubmit;
import com.lind.lock.template.RedisUserManualLockTemplate;
import com.lind.start.test.dto.UserDTO;
import com.lind.start.test.handler.UserEvent;
import com.lind.start.test.handler.UserEventType;
import com.lind.start.test.handler.listener.EmailEventBusListener;
import com.lind.start.test.handler.listener.SmsEventBusListener;
import com.lind.verification.code.image.ImageCodeProcessor;
import com.lind.verification.code.image.ImageStreamCodeProcessor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Api("测试")
public class HomeController {
    @Autowired
    RedisUserManualLockTemplate redisUserManualLockTemplate;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    EventBusService userEventService;
    @Autowired
    SmsEventBusListener smsEventListener;
    @Autowired
    EmailEventBusListener emailEventListener;
    @Autowired
    ImageCodeProcessor imageCodeProcessor;
    @Autowired
    ImageStreamCodeProcessor imageStreamCodeProcessor;
    @GetMapping("/index")
    public String index() {
        return "success index!";
    }

    /**
     * 不加@RequestBody相当于@RequestParam
     *
     * @param userDTO
     * @return
     */
    @ApiOperation("post参数在url")
    @PostMapping("/p-url")
    public ResponseEntity ptest(@ApiParam("请求体") UserDTO userDTO) {
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/p")
    public ResponseEntity p(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/postUser")
    public ResponseEntity postUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/pget")
    public ResponseEntity pget() {
        return ResponseEntity.ok(
                new UserDTO("zzl", null, false, 5d, BigDecimal.valueOf(100), null, null)
        );
    }

    @GetMapping("/no-get")
    public ResponseEntity noGet() {
        return ResponseEntity.ok(
                new UserDTO("zzl", null, false, 5d, BigDecimal.valueOf(100), null, null)
        );
    }

    /**
     * 同步锁.
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/lockAndLimit", method = RequestMethod.GET)
    public Object test1(HttpServletRequest request, @RequestParam String sourceId, @RequestParam String userId) throws InterruptedException {
        request.getSession().setAttribute("id", userId);
        return redisUserManualLockTemplate.execute(sourceId, 60 * 5, TimeUnit.SECONDS);
    }

    /**
     * 删除锁
     *
     * @return
     */
    @RequestMapping(value = "/remove/{sourceId}", method = RequestMethod.GET)
    public void remove(@PathVariable String sourceId) {
        redisUserManualLockTemplate.unLock(sourceId);
    }

    /**
     * 锁定资源列表.
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list() {
        return ResponseEntity.ok(redisUserManualLockTemplate.getSourceList());
    }

    @RepeatSubmit()
    @RequestMapping(value = "/repeat", method = RequestMethod.GET)
    public String repeat() {
        return "OK";
    }

    /**
     * 对应重定向咱们：/callback?token=abc123，咱们再把token重定向到其它网站
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> params = request.getParameterMap();

        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String key = param.getKey();
            String value = StringUtils.join(param.getValue());
            log.info("key={},value={}", key, value);
        }
        String token = StringUtils.join(params.get("token"));
        response.sendRedirect("http://www.baidu.com?token=" + token);
    }

    @GetMapping("/event2")
    public void event2() {
        userEventService.addEventListener(emailEventListener, UserEventType.LOGIN.name());

    }

    @GetMapping("/event1")
    public void event1() {
        userEventService.addEventListener(smsEventListener, UserEventType.LOGIN.name());
        userEventService.publisher(new UserEvent("1", "hello"), UserEventType.LOGIN.name());
    }

    @GetMapping("/test/image-code")
    public void imageGenerate(ServletWebRequest request, HttpServletResponse response) throws Exception {
        imageCodeProcessor.create(request);

    }

    @GetMapping("/test/image-stream")
    public void imageStreamGenerate(ServletWebRequest request, HttpServletResponse response) throws Exception {
        imageStreamCodeProcessor.create(request);

    }
}