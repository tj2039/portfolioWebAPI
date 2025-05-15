package com._2je7.pofol.Dto.Login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
	@Schema(description = "로그인 아이디", requiredMode = Schema.RequiredMode.REQUIRED, example = "아이디")
    @NotBlank(message = "로그인 아이디는 필수 값입니다.")
    private String id;
	
	@Schema(description = "로그인 비밀번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "비밀번호")
    @NotBlank(message = "로그인 패스워드는 필수 값입니다.")
    private String pw;
	
	@Schema(description = "ip 주소", hidden = true)
    private String ipAddress;
}
