package ru.mtven.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mtven.springdemo.model.Users;
import ru.mtven.springdemo.repository.UserRepository;

import java.util.Collections;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users users = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return new User(
                users.getLogin(),
                users.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
