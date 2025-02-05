package com._2je7.pofol.Service.Auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com._2je7.pofol.Dao.Login.LoginDao;
import com._2je7.pofol.Entity.TbUserEntity;
import com._2je7.pofol.Repository.TbUser.TbUserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	TbUserRepository tbUserRepo;
	
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String fullId) throws UsernameNotFoundException {    		
    	TbUserEntity userEntity = null;
    	User user = null;
    	Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    	
    	try{
    		String id = fullId;
    		
    		if(id.length() > 0 && id.isEmpty() == false) {
    			userEntity = tbUserRepo.findByUserLgnId(id);
    			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getUserGrd()));
    		}	
    		
    		user = new User(userEntity.getUserLgnId().toString(), userEntity.getUserLgnPswd(), grantedAuthorities);
    	}
    	catch (Exception err) {
    		log.error(err.getMessage());
		}
        
        if(user != null) {
        	return user;
        }
        else {
        	return null;
        }
    }
}
