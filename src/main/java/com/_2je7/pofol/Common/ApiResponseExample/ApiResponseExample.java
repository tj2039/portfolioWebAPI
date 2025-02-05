package com._2je7.pofol.Common.ApiResponseExample;

public class ApiResponseExample {
	public static final String OK_description = "API 성공 결과값";
	public static final String FAIL_description = "API 실패 결과값";
	public static final String API_developing = "API 작업중";
	public static final String JWT_ERROR_description = "JWT 에러";
	public static final String TOKEN_ISSUANCE 
		= "{\r\n"
		+ "  \"code\": 4001,\r\n"
		+ "  \"httpStatus\": \"TOKEN_ISSUANCE\",\r\n"
		+ "  \"message\": \"액세스 토큰 발급 필요\",\r\n"
		+ "  \"count\": -1\r\n"
		+ "}";
	public static final String PERMISSION_DENINED 
		= "{\r\n"
		+ "  \"code\": 4008,\r\n"
		+ "  \"httpStatus\": \"PERMISSION\",\r\n"
		+ "  \"message\": \"해당 API 사용 권한 없음\",\r\n"
		+ "  \"count\": -1\r\n"
		+ "}";
	public static class Login{
		public static final String tryLogin_2000 = API_developing;
		public static final String tryLogin_4009 = API_developing;

	}
	/**
	 * apiSummary ApiResponseExample
	 * */
	public static final String apiNameCode = "";
	public static final String apiNameCode_2004
		= "{\r\n"
		+ "  \"code\": 2004,\r\n"
		+ "  \"httpStatus\": \"OK_EMPTY\",\r\n"
		+ "  \"message\": \"즐겨찾기 조회 데이터가 없습니다.\",\r\n"
		+ "  \"count\": 0,\r\n"
		+ "  \"result\": []\r\n"
		+ "}";
	public static final String apiNameCode_4005
		= "{\r\n"
		+ "  \"code\": 4005,\r\n"
		+ "  \"httpStatus\": \"DB_ERROR\",\r\n"
		+ "  \"message\": \"즐겨찾기 삭제 실패\",\r\n"
		+ "  \"count\": 0\r\n"
		+ "}";
	public static class controllerName{
		
	}
}
