package com._2je7.pofol.Config;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com._2je7.pofol.Dto.Common.Response.CommonResponse;
import com._2je7.pofol.Enum.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
    	CommonResponse<Object> commonResponse = CommonResponse.builder()
    			.code(ResponseCode.PERMISSION.value())
				.httpStatus(ResponseCode.PERMISSION)
				.message("해당 API 사용 권한 없음")
				.count(-1)
				.build();
    	
    	response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        mapper.writeValue(response.getOutputStream(), commonResponse);
    }
}
