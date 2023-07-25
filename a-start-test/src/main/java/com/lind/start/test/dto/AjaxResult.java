package com.lind.start.test.dto;

import java.util.HashMap;

/**
 * @author lind
 * @date 2023/7/25 15:49
 * @since 1.0.0
 */
public class AjaxResult extends HashMap<String, Object> {

	public AjaxResult(String msg) {
		super.put("message", msg);
	}

	public static AjaxResult success() {
		return new AjaxResult("成功");
	}

	public static AjaxResult success(String message) {
		return new AjaxResult(message);
	}

}
