package com.spring.security.chp2.service;

//@Service
//public class JpaUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public CustomUserDetails loadUserByUsername(String username) {
//        Supplier<UsernameNotFoundException> s =
//                () -> new UsernameNotFoundException(
//                        "Problem during authentication!");
//        User u = userRepository
//                .findUserByUsername(username)
//                .orElseThrow(s);
//        return new CustomUserDetails(u);
//    }
//}


import com.spring.security.chp2.config.CustomUserDetails;
import com.spring.security.chp2.entity.User;
import com.spring.security.chp2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService {
    @Autowired
    private UserRepository userRepository;

   public CustomUserDetails findUserByName(String name) {
       User user = userRepository.findUserByUsername(name).get();
       return new CustomUserDetails(user);
   }
}