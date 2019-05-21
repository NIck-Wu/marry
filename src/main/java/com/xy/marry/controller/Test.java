package com.xy.marry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xy.marry.entity.User;
import com.xy.marry.exception.MyException;
import com.xy.marry.server.UserService;

@RestController
@RequestMapping("/api/test")
public class Test {

	@Autowired
	private StringRedisTemplate template;

	@Autowired
	private UserService userService;

	/**
	 * 查询
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "find", method = RequestMethod.GET)
	public String find() {
		// 没有这个key
		if (!template.hasKey("1")) {
			User userQuery = userService.findById(1);
			template.opsForValue().append("1", String.valueOf(userQuery));
			System.out.println("get data from Mysql ");
			return String.valueOf(userQuery);
		}
		String val = template.opsForValue().get("1");// 根据key获取缓存中的val
		System.out.println("get data from redis ");
		return val;
	}

	@RequestMapping("/hello")
	public String hello() {
		throw new RuntimeException();
//        return "Hello Spring Boot!";
	}

	@RequestMapping("/hello1")
	public String hello1() {
		throw new IllegalArgumentException();
//        return "Hello Spring Boot!";
	}

	@RequestMapping("/hello2")
	public String hello2() {
		throw new MyException("我是主动抛出来的");
//        return "Hello Spring Boot!";
	}

//	@RequestMapping("/hello3")
//	public Result<User> hello3() {
//		User user = userService.findById(1);
//		return Result.success(user);
//	}

}
