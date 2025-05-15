package com._2je7.pofol.Dto.Login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogoutRequestDto {
	@Schema(description = "로그인 아이디", hidden = true)
    @NotBlank(message = "로그인 아이디는 필수 값입니다.")
    private String id;
}
