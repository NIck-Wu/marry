package com.xy.marry.controller.websocket;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xy.marry.exception.ResponseResult;
import com.xy.marry.implement.websocket.MyWebSocket;

@RestController
@RequestMapping("/api/webscoket/webscoket")
public class WebscoketController {

	/**
	 * 查询或者添加用户信息
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "sendAll", method = RequestMethod.POST)
	public ResponseResult<JSONObject> find(@RequestBody JSONObject pushData) {
		try {
			MyWebSocket.sendInfo(pushData.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseResult<>();
		}
}
