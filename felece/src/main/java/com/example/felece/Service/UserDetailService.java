package com.example.felece.Service;

import com.example.felece.Repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

import com.example.felece.Model.Person;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Service
public class UserDetailService implements UserDetailsService {
    
    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        final Person _user = personRepository.findByEmail(email);
        if (_user == null) {
            throw new UsernameNotFoundException(email);
        }

        if(_user.getRole().getId()==1){
            UserDetails user = User.withUsername(_user.getEmail()).password(_user.getPassword()).roles("ADMIN").build();
            return user;
        }
        else{
            UserDetails user = User.withUsername(_user.getEmail()).password(_user.getPassword()).roles("USER").build();
            return user;
        }
  
    }




}
