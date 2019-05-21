package com.xy.marry.utils;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xy.marry.common.ErrorCodeEnum;


/**
 * json 格式输入 公用
 * 
 * @author cjx SpringUtils.java 2013-12-6
 */
public class SpringUtils {

	// header 常量定义
	private static final String ENCODING_PREFIX = "encoding";
	private static final String NOCACHE_PREFIX = "no-cache";
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;
	private static Logger logger = LogManager.getLogger(SpringUtils.class);

	/**
	 * 获得bean
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz, String name) {
		return (T) WebApplicationContextUtils.getWebApplicationContext(
				ContextLoader.getCurrentWebApplicationContext()
						.getServletContext()).getBean(name);
	}

	/**
	 * 直接输出内容的简便函数.
	 * 
	 */
	public static void render(HttpServletResponse response,
			final String contentType, final String content) {
		try {
			// 分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			// 设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);
			response.getWriter().flush();

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * 
	 */
	public static void renderText(HttpServletResponse response,
			final String text) {
		render(response, "text/plain", text);
	}

	/**
	 * 直接输出HTML.
	 * 
	 */
	public static void renderHtml(HttpServletResponse response,
			final String html) {
		render(response, "text/html", html);
	}

	/**
	 * 直接输出XML.
	 * 
	 */
	public static void renderXml(HttpServletResponse response,
			final String xml) {
		render(response, "text/xml", xml);
	}

	/**
	 * 直接输出JSON格式
	 * 
	 * @param string
	 *            json字符串.
	 */
	public static void renderJson(HttpServletResponse response,
			final String string) {
		render(response, "application/json", string);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * 将Map对象,将被转化为json字符串.
	 */
	@SuppressWarnings("rawtypes")
	public static void renderJson(HttpServletResponse response, final Map map) {
		String jsonString = JSON.toJSONString(map);
		renderJson(response, jsonString);
	}

	
	/**
	 * 直接输出JSON格式
	 * 
	 * @param ErrorCodeEnum
	 *            erroeCodeEnum 枚举状态码.
	 */
	public static void renderJson(HttpServletResponse response,
			final ErrorCodeEnum erroeCodeEnum) {
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("code", erroeCodeEnum.getCode());
		jsonObject.put("msg", erroeCodeEnum.getDesc());
		jsonObject.put("requestId", UUID.randomUUID().toString());
		render(response, "application/json", jsonObject.toJSONString());
	}

 
	 

}
