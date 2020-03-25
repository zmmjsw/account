package com.zmm.account.util;

import java.util.List;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
/**
 * 
* @ClassName: ValidateUtility 
* @Description: TODO(自定义注解异常捕获工具类) 
* @author zhumingming 
* @date 2018年11月23日 下午3:10:10 
*
 */
public class ValidateUtility {

	public static String judgeValidate(BindingResult result) {
			List<ObjectError> list = result.getAllErrors();
			StringBuilder stringBuilder = new StringBuilder();
			for (ObjectError error : list) {
				Object[] arguments = error.getArguments();
				DefaultMessageSourceResolvable de = (DefaultMessageSourceResolvable) arguments[0];
				String code = de.getCode();
				stringBuilder.append("/n" + error.getDefaultMessage() + "--错误列" + code);
			}
			return stringBuilder.toString();
	}

}
