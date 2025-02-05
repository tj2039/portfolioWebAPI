package com._2je7.pofol.Dao.Login;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.client.HttpStatusCodeException;

import com._2je7.pofol.Dto.Login.LoginResponseDto;

@Mapper
public interface LoginDao {
	public LoginResponseDto getTbUserByUserLgnId(String id) throws HttpStatusCodeException;
}
