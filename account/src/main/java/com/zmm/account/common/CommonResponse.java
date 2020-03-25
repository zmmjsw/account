package com.zmm.account.common;

import com.zmm.account.common.enums.ResponseStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName: CommonResponse
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhumingming
 * @date 2020年3月25日
 *
 * @param <T>
 */
@Data
public class CommonResponse<T> {
	@ApiModelProperty(value = "状态信息")
	private Integer code;
	@ApiModelProperty(value = "状态码")
	private String message;
	@ApiModelProperty(value = "详情")
	private T data;

	public CommonResponse() {
		this.code = ResponseStatusEnum.SUCCESS.getCode();
		this.message = ResponseStatusEnum.SUCCESS.getMessage();
	}

	public CommonResponse(T object) {
		this.code = ResponseStatusEnum.SUCCESS.getCode();
		this.message = ResponseStatusEnum.SUCCESS.getMessage();
		this.data = object;

	}

	public CommonResponse(ResponseStatusEnum responseStatusEnum) {
		this.code = responseStatusEnum.getCode();
		this.message = responseStatusEnum.getMessage();
	}

	public CommonResponse(T object, ResponseStatusEnum responseStatusEnum) {
		this.code = responseStatusEnum.getCode();
		this.message = responseStatusEnum.getMessage();
		this.data = object;
	}
	public CommonResponse(Integer status,String message,T obj) {
		this.code = status;
		this.message = message;
		this.data = obj;
	}
	

	public static <T> CommonResponse<T> build() {
		return new CommonResponse<T>();
	}

	public static <T> CommonResponse<T> build(ResponseStatusEnum responseStatusEnum) {
		return new CommonResponse<T>(responseStatusEnum);
	}

	public static <T> CommonResponse<T> build(T obj) {
		return new CommonResponse<T>(obj);
	}

	public static <T> CommonResponse<T> build(T obj, ResponseStatusEnum responseStatusEnum) {
		return new CommonResponse<T>(obj, responseStatusEnum);
	}
	
	public static <T> CommonResponse<T> build(Integer status,String message,T obj) {
		return new CommonResponse<T>(status,message,obj);
	}

}
