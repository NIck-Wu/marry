package com.xy.marry.server.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.xy.marry.implement.websocket.MyWebSocket;
import com.xy.marry.server.WebSocketService;

import okhttp3.WebSocket;

@Service
@Transactional
public class WebSocketServiceImpl implements WebSocketService {

	/**
	 * 给所有连接到websocket的用户推送信息
	 */
	@Override
	public void pushAll(JSONArray json) {

		try {
			MyWebSocket.sendInfo(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给所有连接到websocket的用户推送信息
	 */
	@Override
	public void pushAll(String message) {
		try {
			MyWebSocket.sendInfo(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取websocket对象
	 */
	@Override
	public WebSocket getWebSocket(HttpServletRequest request) {
		
		//TODO
		
		return null;
	}

}
