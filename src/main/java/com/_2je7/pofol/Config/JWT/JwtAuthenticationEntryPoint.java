package com._2je7.pofol.Config.JWT;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com._2je7.pofol.Dto.Common.Response.CommonResponse;
import com._2je7.pofol.Enum.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException, ServletException {

		//log.error(err.getMessage());
		//map = responseManager.getErrorMap(err);
		//responseManager.setHttpStatusCode(response, map);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	CommonResponse<Object> commonResponse = CommonResponse.builder()
    			.code(ResponseCode.TOKEN_ISSUANCE.value())
				.httpStatus(ResponseCode.TOKEN_ISSUANCE)
				.message("액세스 토큰 발급 필요")
				.count(-1)
				.build();
    	
    	response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        mapper.writeValue(response.getOutputStream(), commonResponse);
        
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized");
	}
}