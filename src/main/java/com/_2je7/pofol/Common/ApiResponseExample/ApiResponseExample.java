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
		/**
		 * 로그인 ApiResponseExample
		 * */
		public static final String tryLogin_2000 = "{\n"
				+ "  \"code\": 2000,\n"
				+ "  \"httpStatus\": \"OK\",\n"
				+ "  \"message\": \"로그인 성공\",\n"
				+ "  \"count\": 1,\n"
				+ "  \"result\": {\n"
				+ "    \"userLgnId\": \"alice\",\n"
				+ "    \"userNm\": \"관리자\",\n"
				+ "    \"accessToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhbGljZSIsInN1YiI6ImFsaWNlIiwiaWF0IjoxNzM4NzQ0MzI1LCJleHAiOjE3Mzg3ODc1MjV9.Act_YBbNgnOdlMHvX2chwBzqOXb0E1wD8ArlOrCy-JM\",\n"
				+ "    \"refreshToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3Mzg3NDQzMjUsImV4cCI6MTczODgzMDcyNX0.xYdrUob2--H5AYx3UjE-ncpl22fka6kD76xCF3JTg-U\",\n"
				+ "    \"accessTokenExpireDate\": 43200000\n"
				+ "  }\n"
				+ "}";
		public static final String tryLogin_4009 = "{\n"
				+ "  \"code\": 4009,\n"
				+ "  \"httpStatus\": \"IMMIGRATION_FAIL\",\n"
				+ "  \"message\": \"입력하신 아이디와 패스워드를 확인해 주세요.\",\n"
				+ "  \"count\": 0\n"
				+ "}";
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
