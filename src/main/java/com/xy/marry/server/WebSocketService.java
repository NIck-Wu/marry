package com.xy.marry.server;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;

import okhttp3.WebSocket;

public interface WebSocketService {

	/**
	 * 给所有连接到websocket的用户推送信息
	 */
	public void pushAll(JSONArray json);

	/**
	 * 给所有连接到websocket的用户推送信息
	 */
	public void pushAll(String message);

	/**
	 * 获取websocket对象
	 * 
	 * @param request
	 * @return
	 */
	public WebSocket getWebSocket(HttpServletRequest request);

}
