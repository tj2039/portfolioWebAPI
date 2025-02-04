package com._2je7.pofol.Enum;

public enum ResponseCode {
	/* 20xx : 성공 */
	//2000: 조회 성공 + 데이터 존재 시(result 있음)
	OK(2000,"조회 성공 + 데이터 존재"),
	//2001: POST으로 인한 데이터 생성 성공 시(result 없음)
	CREATED(2001,"POST으로 인한 데이터 생성 성공"),
	//2002: PUT, DELETE로 인한 데이터 변경 성공 시(result 없음)
	MODIFY(2002,"PUT, DELETE로 인한 데이터 변경 성공"),
	//2004: 조회 성공 + 데이터 없을 시(result 없음)
	OK_EMPTY(2004,"조회 성공 + 데이터 없음"),

	
	/* 40xx: 실패 */
	//4000: 필수 파라미터 누락
	MISSING_PARAMETER(4000,"필수 파라미터 누락"),
	//4001: 액세스 토큰 발급 필요
	TOKEN_ISSUANCE(4001,"액세스 토큰 발급 필요"),
	//4002: PUT, DELETE 시 존재하지 않는 데이터 변경요청
	NOT_EXIST(4002,"PUT, DELETE 시 존재하지 않는 데이터 변경요청"),
	//4003: 이미 존재하는 데이터 입력
	ALREADY_EXIST(4003,"이미 존재하는 데이터 입력"),
	//4004: Exception error(서버로그 확인 필요)
	EXCEPTION_ERROR(4004,"Exception error(서버로그 확인 필요)"),
	//4005: db연결 에러
	DB_ERROR(4005,"db연결 에러"),
	//4006: 서버 점검 중
	DB_UNAVAILABLE(4006,"서버 점검 중"),
	//4007: 파라미터 타입 에러
	INVALID_PARAMETER(4007,"파라미터 타입 에러"),
	//4008: 권한 없음
	PERMISSION(4008,"권한 없음"),
	//4009: 인증 실패
	IMMIGRATION_FAIL(4009,"입력하신 아이디와 패스워드를 확인해 주세요.");
	
	private final int value;
	private final String message;
	
	ResponseCode(int value,String message){
		this.value = value;
		this.message = message;
	}
	
	public int value() {
		return this.value;
	}
	
	public String message() {
		return this.message;
	}
}
