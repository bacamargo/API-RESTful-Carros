package com.example.cars.api.security;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRep.findByLogin(username);
        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return user;
    
}
