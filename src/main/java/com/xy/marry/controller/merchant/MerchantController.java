package com.xy.marry.controller.merchant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
 

	/**
	 * 查询
	 * 
	 * @param user
	 */
	@RequestMapping(value = "find", method = RequestMethod.GET)
	public void find() {
		// 没有这个key
		System.out.println("get data from redis ");
	}

}
