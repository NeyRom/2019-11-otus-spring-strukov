package ru.strukov.springauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.strukov.springauth.domain.BookstoreUser;
import ru.strukov.springauth.repository.BookstoreUserRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author Roman Strukov
 */

@Component
public class BookstoreUserDetailService implements UserDetailsService {
    private final BookstoreUserRepository userRepository;

    @Autowired
    public BookstoreUserDetailService(BookstoreUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BookstoreUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = Collections
                .singletonList(new SimpleGrantedAuthority("user"));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
