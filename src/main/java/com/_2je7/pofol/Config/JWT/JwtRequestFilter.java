package com._2je7.pofol.Config.JWT;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com._2je7.pofol.Dto.Common.ApiCallLogDto;
import com._2je7.pofol.Service.Auth.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    private static final List<String> EXCLUDE_URL =
            Collections.unmodifiableList(
                    Arrays.asList(
                        "/api/login",
                        "/api/refresh",
                        "/resources/**",
                        "/v2/api-docs", 
                        "/v3/api-docs",
                        "/api-docs/**",
                        "/configuration/**", 
                        "/swagger*/**",
                        "/webjars/**"
                    ));
    
    private static final List<String> ALL_EXCLUDE_URL =
            Collections.unmodifiableList(
                    Arrays.asList(
                        "/**"
                    ));
    private static final List<String> SWAGGER_URLS =
            Collections.unmodifiableList(
                    Arrays.asList(
                            "/v2/**",
                            "/v3/**",
                            "/api-docs/**",
                            "/configuration/**",
                            "/swagger",
                            "/swagger-resources/**",
                            "/swagger-ui/**",
                            "/webjars/**",
                            "/swagger-ui.html",
                            "/resources/**"
                    ));
    
    @Autowired
    private JwtUserDetailsService jwtUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		
		// 경로 변수 제거한 기본 경로만 저장
        ApiCallLogDto apiLog = new ApiCallLogDto();
	        apiLog.setEndpoint(request.getServletPath());
	        apiLog.setHttp_method(request.getMethod());
	        apiLog.setRequest_ip(request.getRemoteAddr());
	        apiLog.setUser_agent(request.getHeader("User-Agent"));
        
        String fullId = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
            	fullId = jwtTokenUtil.getIdFromToken(jwtToken);
            	apiLog.setUserId(fullId);
            	log.debug("fullId --> " + fullId);
            } catch (IllegalArgumentException e) {
            	log.debug("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
            	log.debug("JWT Token has expired");
            }
        } else {
        	log.warn("JWT Token does not begin with Bearer String");
        }
        log.info(apiLog.toString());
        log.debug("JwtRequestFilter::doFilterInternal ID : " + fullId);
        
        if(fullId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	
        	   UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(fullId);
        	
            if(userDetails != null && jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            	
                log.debug("JwtRequestFilter::doFilterInternal Validate True");
            }
            else
            {
            	log.debug("JwtRequestFilter::doFilterInternal Validate False");
            }
        }
        
        filterChain.doFilter(request,response);
		
	}
	
	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    	PathMatcher pathMatcher = new AntPathMatcher();
        return SWAGGER_URLS.stream().anyMatch(exclude -> pathMatcher.match(exclude, request.getServletPath()));
    	//return true;
    }
}