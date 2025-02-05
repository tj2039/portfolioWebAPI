package com._2je7.pofol.Service.Login;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com._2je7.pofol.Common.Utility.SHA256;
import com._2je7.pofol.Config.JWT.JwtTokenUtil;
import com._2je7.pofol.Dao.Login.LoginDao;
import com._2je7.pofol.Dto.Common.Response.CommonResponse;
import com._2je7.pofol.Dto.Login.LoginRequestDto;
import com._2je7.pofol.Dto.Login.LoginResponseDto;
import com._2je7.pofol.Entity.TbUserEntity;
import com._2je7.pofol.Enum.ResponseCode;
import com._2je7.pofol.Repository.TbUser.TbUserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	MessageSource message;
	
	@Autowired
	TbUserRepository tbUserRepo;
	
	@Autowired
	LoginDao loginDao;
	
	@Value("${api.access.token.expire}")
	Integer accessTokenExpire;
	
	@Override
	public CommonResponse tryLogin(LoginRequestDto login, HttpServletRequest request) throws IOException {
		CommonResponse commonResponse = new CommonResponse<>();
		Locale locale = request.getLocale();
		
		try {
			//아이디로 사용자 계정 조회
			TbUserEntity tbUser =  tbUserRepo.findByUserLgnId(login.getId());
			
			//계정 유무 확인
			if(tbUser == null) {
				commonResponse = CommonResponse.builder()
						.code(ResponseCode.IMMIGRATION_FAIL.value())
						.httpStatus(ResponseCode.IMMIGRATION_FAIL)
						.message(message.getMessage("Login.tryLogin.IMMIGRATION_FAIL.DEACTIVATION", null, locale))
						.count(0)
						.build();
				return commonResponse;
			}
			
			//입력받은 비밀번호와 salt 합쳐서 암호화
			String encryptPw = SHA256.encrypt(login.getPw()+tbUser.getSalt());
			
			//암호화한 비밀번호와 확인
			if(!encryptPw.equals(tbUser.getUserLgnPswd())) {
				commonResponse = CommonResponse.builder()
						.code(ResponseCode.IMMIGRATION_FAIL.value())
						.httpStatus(ResponseCode.IMMIGRATION_FAIL)
						.message(message.getMessage("Login.tryLogin.IMMIGRATION_FAIL.INVAILD", null, locale))
						.count(0)
						.build();
				return commonResponse;
			}
			
			final String token = JwtTokenUtil.generateToken(login.getId());
			final String refreshToken = JwtTokenUtil.generateRefreshToken();
		
			LoginResponseDto loginInfo =  new LoginResponseDto();
			loginInfo.setUserLgnId(tbUser.getUserLgnId());
			loginInfo.setUserNm(tbUser.getUserNm());
			loginInfo.setAccessToken(token);
			loginInfo.setRefreshToken(refreshToken);
			loginInfo.setAccessTokenExpireDate(accessTokenExpire * (60 * 60 * 1000));
			
			commonResponse = CommonResponse.builder()
					.code(ResponseCode.OK.value())
					.httpStatus(ResponseCode.OK)
					.message(message.getMessage("Login.tryLogin.OK", null, locale))
					.count(1)
					.result(loginInfo)
					.build();
			
		} catch (HttpStatusCodeException e) {
			log.error(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		}
		
		return commonResponse;
	}

}
