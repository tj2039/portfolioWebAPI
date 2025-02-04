package com._2je7.pofol.Config.JWT;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    MemberDao memberDao;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();

        try{
	        Map<String, String> parms = new HashMap<>();
	    	parms.put("id", id);
	    	parms.put("pw", password);
	        /*
	    	if(id.length() > 0 && id.isEmpty() == false) {
	    		MemberInfoDto member = memberDao.getMemberInfo((MemberLoginRequestDto)parms);
	    		
		        if(passwordEncoder.matches(member.getPw(), password)) {
		            throw new BadCredentialsException("UnAuthorized");
		        }
	    	}
	    	*/
        }
        catch(Exception err){
        	
        }
        
        return new UsernamePasswordAuthenticationToken(id, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}