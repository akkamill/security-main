//package com.spring.security.chp2.config;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.List;
//
//public class InMemoryUserDetailsService implements UserDetailsService {
//
//    private final List<UserDetails> users;
//
//    public InMemoryUserDetailsService(List<UserDetails> users) {
//        this.users = users;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//
//        for (UserDetails ud : users) {
//            if (ud.getUsername().equals(username))
//                return ud;
//        }
//        return null;
//    }
//}
