package com.xy.marry.controller.luckdraw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xy.marry.common.ErrorCodeEnum;
import com.xy.marry.entity.User;
import com.xy.marry.exception.ResponseResult;
import com.xy.marry.server.UserService;

@RestController
@RequestMapping("/api/luckdraw")
public class LuckDrawController {
	@Autowired
	private UserService userService;
 //test
	/**
	 * 抽奖
	 * 
	 * @return
	 */
	@RequestMapping(value = "luck", method = RequestMethod.POST)
	public ResponseResult<User> luck() {
		
		User userQuery = userService.luckDraw();
		
		return new ResponseResult<User>(ErrorCodeEnum.SUCCESS.getCode(),
				ErrorCodeEnum.SUCCESS.getDesc(), userQuery);
	}

}
