package com._2je7.pofol.Service.Login;

import java.io.IOException;

import com._2je7.pofol.Dto.Common.Response.CommonResponse;
import com._2je7.pofol.Dto.Login.LoginRequestDto;

import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {
	 public CommonResponse tryLogin(LoginRequestDto login, HttpServletRequest request) throws IOException;
}
