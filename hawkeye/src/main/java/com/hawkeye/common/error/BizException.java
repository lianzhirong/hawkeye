package com.hawkeye.common.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	protected int errorCode;
	/**
	 * 错误信息
	 */
	protected String errorMsg;

	public BizException(BaseErrorInfoInterface errorInfoInterface) {
		this.errorCode = errorInfoInterface.getResultCode();
		this.errorMsg = errorInfoInterface.getResultMsg();
	}
	
	public BizException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
		this.errorCode = errorInfoInterface.getResultCode();
		this.errorMsg = errorInfoInterface.getResultMsg();
	}
	
	public BizException(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public BizException(int errorCode, String errorMsg, Throwable cause) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}