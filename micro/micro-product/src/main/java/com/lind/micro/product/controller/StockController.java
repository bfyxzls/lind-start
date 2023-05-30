/*
 *  Copyright 1999-2021 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.lind.micro.product.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lind.micro.product.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Program Name: springcloud-nacos-seata
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/8/28 4:05 PM
 */
@RestController
@RequestMapping("stock")
@Slf4j
public class StockController {

	@Resource
	private StockService stockService;

	/**
	 * 减库存
	 * @param commodityCode 商品代码
	 * @param count 数量
	 * @return
	 */
	@SentinelResource(value = "deduct")
	@RequestMapping(path = "/deduct")
	public Boolean deduct(String commodityCode, Integer count, String password) {
		log.info("请求库存微服务password：{}", password);
		stockService.deduct(commodityCode, count);
		return true;
	}

	@SentinelResource
	@RequestMapping(path = "/hello")
	public ResponseEntity hello() {
		return ResponseEntity.ok("ok");
	}

	@RequestMapping(path = "/hello2")
	public ResponseEntity hello2() {
		return ResponseEntity.ok("ok");
	}

}
