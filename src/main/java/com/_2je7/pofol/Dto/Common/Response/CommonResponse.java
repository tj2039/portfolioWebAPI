package com._2je7.pofol.Dto.Common.Response;

import com._2je7.pofol.Enum.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공통 응답 객체")
public class CommonResponse<T> {
	private Integer code;
    private ResponseCode httpStatus;
    private String message;
    private Integer count;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;
    
    @Override
    public String toString() {
        return "CommonResponse [code=" + code + ", httpStatus=" + httpStatus + ", message=" + message + ", count=" + count + "]";
    }
}
