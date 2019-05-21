package com.xy.marry.controller.project.qjxcx;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xy.marry.common.Constants;
import com.xy.marry.common.ErrorCodeEnum;
import com.xy.marry.entity.ProjectQjxcx;
import com.xy.marry.entity.ProjectQjxcxPhotos;
import com.xy.marry.exception.ResponseResult;
import com.xy.marry.server.ProjectQjxcxPhotosService;

@RestController
@RequestMapping("/api/project/qjxcx/photos")
public class QjxcxPhotosController {
	@Autowired
	private ProjectQjxcxPhotosService projectQjxcxPhotosService;

	/**
	 * 查询请柬轮播图照片信息
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public ResponseResult<List<ProjectQjxcxPhotos>> find(
			@RequestBody Map<String, Object> reqMap,HttpServletRequest request) {
		ProjectQjxcx projectQjxcx=(ProjectQjxcx) request.getAttribute(Constants.PROJECT_QJXCX);
		List<ProjectQjxcxPhotos> projectQjxcxPhotosList = projectQjxcxPhotosService
				.findByQjxcxId(projectQjxcx.getId());
		return new ResponseResult<List<ProjectQjxcxPhotos>>(
				ErrorCodeEnum.SUCCESS, projectQjxcxPhotosList);

	}

}
