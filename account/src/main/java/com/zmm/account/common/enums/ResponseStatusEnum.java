package com.zmm.account.common.enums;

/**
 * 
* @ClassName: ResponseStatus 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhumingming 
* @date 2018年6月4日 下午4:07:28 
*
 */
public enum ResponseStatusEnum {
	    SUCCESS(200, "操作成功！"),
	    LOGIN_SUCCESS(200, "登录成功！"),
	    NOT_LOGIN(403, "请先登录"),
	    ERROR(500, "服务器未知错误！"),
	    FORBIDDEN(500, "您没有操作权限！"),
	    LOGIN_ERROR(403, "账号或密码错误！"),
	    USER_EXIST(500, "已存在的用户！"),
	    INVALID_TOKEN(500, "无效的TOKEN，您没有操作权限！"),
	    INVALID_ACCESS(500, "无效的请求，该请求已过期！"),
	    DELETE_ERROR(500, "删除失败！"),
	    NOT_VALID(400, "参数错误！");
	

	    private Integer code;
	    private String message;

	    ResponseStatusEnum(Integer code, String message) {
	        this.code = code;
	        this.message = message;
	    }

	    public static ResponseStatusEnum getResponseStatus(String message) {
	        for (ResponseStatusEnum ut : ResponseStatusEnum.values()) {
	            if (ut.getMessage().equals(message)) {
	                return ut;
	            }
	        }
	        return null;
	    }

	    public Integer getCode() {
	        return code;
	    }

	    public String getMessage() {
	        return message;
	    }

}
