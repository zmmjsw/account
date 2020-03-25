package com.zmm.account.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.zmm.account.common.CommonResponse;
import com.zmm.account.common.enums.ResponseStatusEnum;
import com.zmm.account.util.ValidateUtility;

/**
 * 
 * @ClassName: GlobalExceptionHandler
 * @Description: TODO(全局异常统一处理)
 * @author zhumingming
 * @date 2017年12月7日 下午5:55:14
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 * @Title: notFount
	 * @Description: TODO(拦截业务异常)
	 * @return ErrorInfo 返回类型
	 */
	@ExceptionHandler(MyException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResponse<String> notFount(MyException e, HttpServletRequest req) {
		log.info("错误信息{},错误路径{}", e.getMessage(), req.getRequestURL().toString());
		return CommonResponse.build(e.getCode(), e.getMessage(), req.getRequestURL().toString());
	}

	/**
	 * 
	 * @Title: notFount
	 * @Description: TODO(拦截未知的运行时异常)
	 * @return ErrorInfo 返回类型
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResponse<String> notFount(RuntimeException e, HttpServletRequest req) {
		log.info("错误信息{},错误路径{}", e.getMessage(), req.getRequestURL().toString());
		return CommonResponse.build(ResponseStatusEnum.ERROR);
	}

	/**
	 * 
	 * @Title: notFount
	 * @Description: TODO(拦截参数错误异常)
	 * @return ErrorInfo 返回类型
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResponse<String> notFount(MethodArgumentNotValidException e, HttpServletRequest req) {
		log.info("错误信息{},错误路径{},错误描述{}", e.getMessage(), req.getRequestURL().toString(),
				ValidateUtility.judgeValidate(e.getBindingResult()));
		return CommonResponse.build(ResponseStatusEnum.NOT_VALID);
	}

	/**
	 * 
	 * @Title: notFount
	 * @Description: TODO(拦截参数错误异常)
	 * @return ErrorInfo 返回类型
	 */
	@ExceptionHandler(ArithmeticException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResponse<String> notFount(ArithmeticException e, HttpServletRequest req) {
		log.info("错误信息{},错误路径{}", e.getMessage(), req.getRequestURL().toString());
		return CommonResponse.build(ResponseStatusEnum.ERROR);
	}

}
