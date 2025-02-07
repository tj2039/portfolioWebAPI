package com._2je7.pofol.Controller.Login;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._2je7.pofol.Common.ApiResponseExample.ApiResponseExample;
import com._2je7.pofol.Common.Utility.ResponseUtil;
import com._2je7.pofol.Dto.Common.Response.CommonResponse;
import com._2je7.pofol.Dto.Login.LoginRequestDto;
import com._2je7.pofol.Dto.Login.LoginResponseDto;
import com._2je7.pofol.Service.Login.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Login(*로그인*)", description = "회원 관리 및 로그인 관련 API")
@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {
	private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";

    @Autowired
    LoginService loginService;

    @Operation(
        summary = "사용자 로그인(완료)", 
        description = "사용자 로그인을 할 수 있다."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "로그인 성공",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class),
                examples = @ExampleObject(name = "2000", value = ApiResponseExample.Login.tryLogin_2000))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class),
                examples = @ExampleObject(name = "4009", value = ApiResponseExample.Login.tryLogin_4009))),
        @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping(LOGIN)
    public ResponseEntity<CommonResponse<LoginResponseDto>> tryLogin( @Validated @RequestBody LoginRequestDto member, BindingResult result, HttpServletRequest request) throws IOException {
        log.info("MemberController::login " + member.toString());

        //로그인 IP 주소 저장
        member.setIpAddress(request.getRemoteAddr());
        
        CommonResponse commonResponse = loginService.tryLogin(member,request);
        
        if(commonResponse != null) {
        	log.info(commonResponse.toString());
        }
        
        return new ResponseEntity<>(commonResponse, ResponseUtil.getHttpStatus(commonResponse));
    }
}
