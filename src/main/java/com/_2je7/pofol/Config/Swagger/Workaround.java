package com._2je7.pofol.Config.Swagger;

/**
 * 스웨거 servers 설정 
 * 80을 닫고 자동으로 443으로 이동하도록 설정하면 해결
 */
public class Workaround{
	
}
//@Component
//public class Workaround implements WebMvcOpenApiTransformationFilter {
//	@Override
//	public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
//		HttpServletRequest request = context.request().orElse(null);
//		//ContextPath가 없거나 /swagger-ui 둘다 없으면 기본 url
//		String baseUrl = String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
//		//ContextPath 있으면 ContextPath으로 substring
//		if(request.getContextPath()!=null && !request.getContextPath().equals("")) {
//			baseUrl = request.getHeader("Referer").substring(0,request.getHeader("Referer").lastIndexOf(request.getContextPath()));
//		}else if(!request.getHeader("Referer").contains("/swagger-ui")) { //ContextPath 없지만 "/swagger-ui"있다면   "/swagger-ui"으로 substring
//			baseUrl = request.getHeader("Referer").substring(0,request.getHeader("Referer").lastIndexOf("/swagger-ui"));
//		}
//		/*
//		System.out.println("getContextPath : "+request.getContextPath());
//		System.out.println("getServletPath : "+request.getServletPath());
//		System.out.println("getRequestURI : "+request.getRequestURI());
//		System.out.println("getServerPort : "+request.getServerPort());
//        System.out.println("url : "+baseUrl);
//        */
//		OpenAPI openAPI = context.getSpecification();
//        Server server = new Server();
//        server.setDescription("server url");
//        server.setUrl(baseUrl);
//
//        /*
//        Server localServer = new Server();
//        localServer.setDescription("local Server");
//        localServer.setUrl("http://localhost:8080");
//        */
//
//        openAPI.setServers(Arrays.asList(server/*, localServer*/));
//        return openAPI;
//	}
//
//	@Override
//	public boolean supports(DocumentationType delimiter) {
//		return delimiter.equals(DocumentationType.OAS_30);
//	}
//}