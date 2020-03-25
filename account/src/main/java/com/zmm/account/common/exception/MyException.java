package com.zmm.account.common.exception;

import com.zmm.account.common.enums.ResponseStatusEnum;

/**
 * 
 * @ClassName: MyException
 * @Description: TODO(自定义异常)
 * @author zhumingming
 * @date 2018年6月4日 下午4:03:33
 *
 */
public class MyException extends RuntimeException {

	/**
	 * @Fields serialVersionUID : TODO(序列化)
	 */
	private static final long serialVersionUID = 1L;

	private Integer code;

	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MyException(ResponseStatusEnum ResponseStatusEnum) {
		super();
		this.code = ResponseStatusEnum.getCode();
		this.message = ResponseStatusEnum.getMessage();
	}

	

}
