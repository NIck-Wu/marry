package com.xy.marry.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xy.marry.common.ErrorCodeEnum;
import com.xy.marry.entity.MerchantProject;
import com.xy.marry.entity.User;
import com.xy.marry.exception.ResponseResult;
import com.xy.marry.server.MerchantProjectService;
import com.xy.marry.server.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private MerchantProjectService merchantProjectService;
	@Autowired
	private UserService userService;

	/**
	 * 查询或者添加用户信息
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public ResponseResult<User> find(@RequestBody Map<String, Object> reqMap) {
		MerchantProject merchantProject = merchantProjectService
				.findByAppId(reqMap.get("appid").toString());
		reqMap.put("merchant_id", merchantProject.getMerchantId());
		User user = userService.insertOrSelect(reqMap);
		if (user != null) {
			return new ResponseResult<User>(ErrorCodeEnum.SUCCESS.getCode(),
					ErrorCodeEnum.SUCCESS.getDesc(), user);
		} else {
			return new ResponseResult<User>(ErrorCodeEnum.FAIL.getCode(),
					ErrorCodeEnum.FAIL.getDesc());
		}
	}
}
