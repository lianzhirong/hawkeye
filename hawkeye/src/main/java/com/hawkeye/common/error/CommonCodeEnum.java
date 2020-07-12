package com.hawkeye.common.error;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum CommonCodeEnum implements BaseErrorInfoInterface {
	// 数据操作错误定义
	SUCCESS(200, "成功!"),
	NOT_FOUND(404, "未找到该资源!"),
	INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
	SERVER_BUSY(503,"服务器正忙，请稍后再试!");

	/** 错误码 */
	private int resultCode;

	/** 错误描述 */
	private String resultMsg;


	@Override
	public int getResultCode() {
		return resultCode;
	}

	@Override
	public String getResultMsg() {
		return resultMsg;
	}

}
