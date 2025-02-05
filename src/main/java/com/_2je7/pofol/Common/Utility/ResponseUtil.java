package com._2je7.pofol.Common.Utility;

import org.springframework.http.HttpStatus;

import com._2je7.pofol.Dto.Common.Response.CommonResponse;

public class ResponseUtil {
	public static final Character OK_code = '2';
	
	public static HttpStatus getHttpStatus(CommonResponse commonresponse) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST; 
		if(commonresponse.getCode().toString().charAt(0) == OK_code) {
			httpStatus = HttpStatus.OK;
		}
		return httpStatus;
	}
}
