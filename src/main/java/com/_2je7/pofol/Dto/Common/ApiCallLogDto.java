package com._2je7.pofol.Dto.Common;

import lombok.Data;

@Data
public class ApiCallLogDto {
	private String userId;
	private String endpoint;
    private String http_method;
    private String request_ip;
    private String user_agent;
    
    @Override
    public String toString() {
    	String indent = "    ";
    	return "\nApiCallLogDto [\n"
    			+indent+"userId="+userId+",\n"
    			+indent+"endpoint="+endpoint+",\n"
    			+indent+"http_method="+http_method+",\n"
    			+indent+"request_ip="+request_ip+",\n"
    			+indent+"user_agent="+user_agent+",\n"
    			+"]";
    }
}
