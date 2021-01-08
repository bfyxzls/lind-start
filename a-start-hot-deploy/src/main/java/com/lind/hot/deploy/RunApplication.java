package com.lind.hot.deploy;

import com.lind.common.jackson.convert.EnableJacksonFormatting;
import com.lind.common.util.JarClassLoader;
import com.lind.common.zip.LZWUtils;
import com.lind.hot.deploy.dto.UserDTO;
import com.lind.hot.deploy.scope.EnableScoping;
import com.lind.interfaces.Hello;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@RestController
@EnableJacksonFormatting
@EnableScoping
public class RunApplication {
    public static void main(String[] args) {
        System.out.print("main run...");
        String code = "DBDCDDDEDFDGDHDIFPGGFPDCDADCDADADGDBDCFPEEFPDBDADAFPFGDG";
        System.out.println("rle=" + LZWUtils.lzw_compress(code));
     //   SpringApplication app = new SpringApplication(RunApplication.class);
     //   app.run(args);
    }
    protected final Log log = LogFactory.getLog(this.getClass());
    @SneakyThrows
    @GetMapping(path = "/redirect")
    public void redirect(ServletResponse response) {
        log.debug("hello world!");
        log.info("hello world!");
        log.warn("end.");
      //  HttpServletResponse httpServletResponse = (HttpServletResponse)response;
     //   httpServletResponse.sendRedirect("http://ca.cupl.edu.cn/tpass/login?service=http://contract.cupl.edu.cn:5051");
    }

    @GetMapping(path = "/test")
    public ResponseEntity test() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("lind");
        userDTO.setEmail("123");
        userDTO.setTotal(100d);
        userDTO.setTotalMoney(BigDecimal.valueOf(5000));
        TimeZone time = TimeZone.getTimeZone("Etc/GMT-8");  //转换为中国时区

        TimeZone.setDefault(time);
        userDTO.setBirthday(new Date());
        userDTO.setGetup(new Date());
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("dy")
    public ResponseEntity dy() {
        Hello account = JarClassLoader.joinOuterJarClass("a-start-hot-dependency-1.0.0.jar", Hello.class, "com.lind.hot.HelloImpl");
        Object ret = account.password();
        return ResponseEntity.ok(ret);
    }

}
