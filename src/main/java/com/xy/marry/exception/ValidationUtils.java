package com.xy.marry.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import com.xy.marry.controller.Person;

/**
 * @auther: chenbowen
 * @date: 2019/2/24 15:00
 * @description: 校验工具类
 */

public class ValidationUtils {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static <T> ValidationResult validateEntity(T obj) {
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		// if( CollectionUtils.isNotEmpty(set) ){
		if (set != null && set.size() != 0) {
			result.setHasErrors(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
			}
			result.setErrorMsg(errorMsg);
		}
		return result;
	}

	public static <T> ValidationResult validateProperty(T obj, String propertyName) {
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
		if (set != null && set.size() != 0) {
			result.setHasErrors(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(propertyName, cv.getMessage());
			}
			result.setErrorMsg(errorMsg);
		}
		return result;
	}
	
	

	public static void main(String[] args) {
		Person person = new Person();
		person.setAge(12);
		person.setGender(2);
//       person.setName("李智龙");
		ValidationResult result = ValidationUtils.validateEntity(person);
		Map<String, String> map = result.getErrorMsg();
		boolean isError = result.isHasErrors();
		System.out.println("isError: " + isError);
		System.out.println(map);
	}
}
