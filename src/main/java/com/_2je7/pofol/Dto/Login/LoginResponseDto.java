package com._2je7.pofol.Dto.Login;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginResponseDto {
	@Schema(description = "로그인 아이디", example = "아이디")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String userLgnId;
	
	@Schema(description = "로그인 아이디", example = "아이디")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String userNm;
	
	@Schema(description = "성공 시 전달 되는 Access 토큰", example = "accessToken")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String accessToken;
	
	@Schema(description = "성공 시 전달 되는 Refresh 토큰", example = "refreshToken")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String refreshToken;
	
	@Schema(description = "성공 시 전달 되는 Access 토큰 만료 시간", example = "3600000")
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private Integer accessTokenExpireDate;
}
