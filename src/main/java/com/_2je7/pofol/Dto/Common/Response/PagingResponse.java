package com._2je7.pofol.Dto.Common.Response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingResponse<T> {
	@Schema(description = "전체 조회 목록 수",example = "10")
	private Integer total_items = 0;
	
	@Schema(description = "전체 조회 페이지 수",example = "1")
	private Integer total_page = 0;
	
	@Schema(description = "조회 목록",example = "")
	private List<T> list = new ArrayList<>();
}