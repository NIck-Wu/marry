package com.xy.marry.implement.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.xy.marry.common.Constants.MagicValue;
import com.xy.marry.entity.ProjectQjxcxShake;
import com.xy.marry.entity.ProjectQjxcxShakeUserRanking;
import com.xy.marry.entity.ProjectQjxcxWords;
import com.xy.marry.entity.User;
import com.xy.marry.exception.ResponseResult;
import com.xy.marry.server.ProjectQjxcxShakeService;
import com.xy.marry.server.ProjectQjxcxShakeUserRankingService;
import com.xy.marry.server.ProjectQjxcxWordsService;
import com.xy.marry.server.UserService;

@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
	ApplicationContext act = ApplicationContextRegister.getApplicationContext();
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	@Autowired
	private ProjectQjxcxShakeUserRankingService projectQjxcxShakeUserRankingService = act
			.getBean(ProjectQjxcxShakeUserRankingService.class);
	@Autowired
	private ProjectQjxcxWordsService projectQjxcxWordsService = act
			.getBean(ProjectQjxcxWordsService.class);
	@Autowired
	private ProjectQjxcxShakeService projectQjxcxShakeService = act
			.getBean(ProjectQjxcxShakeService.class);
	@Autowired
	private UserService userService = act.getBean(UserService.class);

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
		try {
			sendMessage(" 你的sessionID为  : " + this.session.getId().toString());
		} catch (IOException e) {
			System.out.println("IO异常");
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {

		JSONObject data = JSONObject.parseObject(message);
		/** type 1 : 摇一摇游戏 2： 祝福留言 */
		Integer type = data.getInteger("type");
		/** operation  1 ：加入游戏 2：开始游戏 3：推送摇一摇数据到redis  */
		Integer operation = data.getInteger("operation");

		JSONObject messageJSON = JSONObject.parseObject(data
				.getString("message"));
		Integer userId = messageJSON.getInteger("userId"); // 用户ID
		Integer shakeId = messageJSON.getInteger("shakeId");// 游戏ID

		if (MagicValue.LENGTH_INTEGER_1 == type) {
			if (MagicValue.LENGTH_INTEGER_1 == operation) {
				try {
					sendMessage(" 你的sessionID为  : "
							+ this.session.getId().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (MagicValue.LENGTH_INTEGER_2 == operation) {
				ProjectQjxcxShake shake = new ProjectQjxcxShake();
				shake.setId(shakeId);
				ResponseResult<ProjectQjxcxShake> responseResult = projectQjxcxShakeService
						.startShakeGame(shake);

				if (!MagicValue.LENGTH_STR_0.equals(responseResult.getCode())) {
					try {
						sendMessage(responseResult.getMsg());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if (MagicValue.LENGTH_INTEGER_3 == operation) {
				// 保存数据到redis
				ProjectQjxcxShakeUserRanking shakeUserRanking = new ProjectQjxcxShakeUserRanking();
				shakeUserRanking.setUserId(userId);
				shakeUserRanking.setNumber(Integer.valueOf(messageJSON
						.getIntValue("num")));
				projectQjxcxShakeUserRankingService
						.addShakeToRedis(shakeUserRanking);
			} else {
				// TODO
			}
		} else {
			/** push all poeples message */
			String blessing = messageJSON.getString("belssing");
			JSONObject json = new JSONObject();
			JSONObject belssMessageJSON = new JSONObject();
			User userQuery = userService.findById(userId);

			if (null == userQuery) {
				belssMessageJSON
						.put("userHeadImgUrl",
								"http://b-ssl.duitang.com/uploads/item/201503/14/20150314215735_Ur8XT.jpeg");
				belssMessageJSON.put("userNickName", "一位不愿透露姓名的网友");
				belssMessageJSON.put("belssing", blessing);
				json.put("type", MagicValue.LENGTH_INTEGER_2);
				json.put("message", belssMessageJSON.toString());
			} else {

				ProjectQjxcxWords projectQjxcxWords = new ProjectQjxcxWords();
				projectQjxcxWords.setUserId(userId);
				projectQjxcxWords.setWords(blessing);
				projectQjxcxWords.setQjxcxId(messageJSON.getInteger("qjxcxId"));
				projectQjxcxWordsService.insertSelective(projectQjxcxWords);

				belssMessageJSON
						.put("userHeadImgUrl", userQuery.getAvatarUrl());
				belssMessageJSON.put("userNickName", userQuery.getNickName());
				belssMessageJSON.put("belssing", blessing);
				json.put("type", MagicValue.LENGTH_INTEGER_2);
				json.put("message", belssMessageJSON.toString());
			}
			for (MyWebSocket item : webSocketSet) {
				try {
					item.sendMessage(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * 发生错误时调用
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
		// this.session.getAsyncRemote().sendText(message);
	}

	/**
	 * 群发自定义消息
	 */
	public static void sendInfo(String message) throws IOException {
		for (MyWebSocket item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				continue;
			}
		}
	}

	/**
	 * 返回当前连接的set列表
	 */
	public static CopyOnWriteArraySet<MyWebSocket> getSetList() {
		return webSocketSet;
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		MyWebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		MyWebSocket.onlineCount--;
	}
}