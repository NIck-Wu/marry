package com.xy.marry.implement.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.xy.marry.common.Constants;
import com.xy.marry.common.Constants.SignType;
import com.xy.marry.common.ErrorCodeEnum;
import com.xy.marry.entity.MerchantProject;
import com.xy.marry.entity.ProjectQjxcx;
import com.xy.marry.implement.BodyReaderHttpServletRequestWrapper;
import com.xy.marry.server.MerchantProjectService;
import com.xy.marry.server.ProjectQjxcxService;
import com.xy.marry.utils.SpringUtils;
import com.xy.marry.utils.XYSDKUtil;

/**
 * xy消费端验签
 * 
 * @author mango 2017.12.28 陈博文
 * @param <T>
 *
 */
@Component
public class InterceptorQjxcxAPI<T> extends HandlerInterceptorAdapter {

	private static Logger log = LogManager.getLogger(InterceptorQjxcxAPI.class);
	@Autowired
	public MerchantProjectService merchantProjectService;
	@Autowired
	private ProjectQjxcxService projectQjxcxService;

	@Override
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("---------------------开始进入请求地址拦截----------------------------");
		String requestData = new BodyReaderHttpServletRequestWrapper(request).getBodyString();
		log.info("拦截器接收到请求参数requestData:{}", requestData);
		if (requestData == null || requestData.isEmpty()) {
			SpringUtils.renderJson(response, ErrorCodeEnum.PARAM_ERROR);
		} else {
			Map<String, Object> data = JSONObject.parseObject(requestData, Map.class);
			if (!data.containsKey(Constants.FIELD_SIGN)) {
				log.info("请柬小程序业务拦截器参数中:sign is null");
				SpringUtils.renderJson(response, ErrorCodeEnum.SIGN_IS_NULL);
			} else if (!data.containsKey(Constants.FIELD_SIGN_TYPE)) {
				log.info("请柬小程序业务拦截器参数中:sign_type is null");
				SpringUtils.renderJson(response, ErrorCodeEnum.SIGN_TYPE_IS_NULL);
			} else if (!data.containsKey(Constants.FIELD_APPID)) {
				log.info("请柬小程序业务拦截器参数中:appid is null");
				SpringUtils.renderJson(response, ErrorCodeEnum.APP_ID_IS_NULL);
			} else {
				// 获取商户信息key
				MerchantProject merchantProject = merchantProjectService
						.findByAppId(data.get(Constants.FIELD_APPID).toString());
				if (merchantProject == null) {
					SpringUtils.renderJson(response, ErrorCodeEnum.MERCHANT_PROJECT_IS_NULL);
				} else {
					String key = merchantProject.getSecretKey();
					if (key == null || "".equals(key)) {
						log.info("请柬小程序业务拦截器参数中:商户信息错误,key is null,appid:{}", data.get(Constants.FIELD_APPID));
						SpringUtils.renderJson(response, ErrorCodeEnum.KEY_IS_NULL);
					} else if (!XYSDKUtil.isSignatureValid(data, key,
							data.get(Constants.FIELD_SIGN_TYPE).toString().equals(Constants.HMACSHA256.toString())
									? SignType.HMACSHA256
									: SignType.MD5)) {
						log.info("请柬小程序拦截器参数中:sign校验错误,appid:{}", data.get(Constants.FIELD_APPID));
						SpringUtils.renderJson(response, ErrorCodeEnum.SIGN_ERROR);
					} else {
						ProjectQjxcx projectQjxcx = projectQjxcxService.findByMerchantProject(merchantProject);
						if (projectQjxcx == null) {
							SpringUtils.renderJson(response, ErrorCodeEnum.MERCHANT_PROJECT_IS_NULL);
						} else {
							request.setAttribute(Constants.PROJECT_QJXCX, projectQjxcx);
							request.setAttribute(Constants.FIELD_KEY, key);
							request.setAttribute(Constants.FIELD_SIGN_TYPE,
									data.get(Constants.FIELD_SIGN_TYPE).toString());
							log.info("请柬小程序业务拦截器参数中:校验成功！appid:{}", data.get(Constants.FIELD_APPID));
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
//		log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
//		log.info("---------------视图渲染之后的操作-------------------------0");
	}

}