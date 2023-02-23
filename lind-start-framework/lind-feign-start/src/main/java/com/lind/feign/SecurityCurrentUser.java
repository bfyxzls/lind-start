package com.lind.feign;

/**
 * @author lind
 * @date 2023/2/23 14:35
 * @since 1.0.0
 */
public class SecurityCurrentUser {

	private static ThreadLocal<String> tl =new ThreadLocal<String>();

	// 存数据
	public static void saveUser(String user){
		tl.set(user);
	}

	//取数据
	public static String getUser(){
		return tl.get();
	}

	//删除数据
	public static void removeUser(){
		tl.remove();
	}


}
