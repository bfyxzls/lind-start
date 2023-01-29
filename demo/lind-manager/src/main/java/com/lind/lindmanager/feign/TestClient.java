package com.lind.lindmanager.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lind
 * @date 2023/1/29 11:55
 * @since 1.0.0
 */
@FeignClient(name = "test", url = "http://localhost:81")
public interface TestClient {

	@GetMapping("j")
	ResponseEntity j();

}
