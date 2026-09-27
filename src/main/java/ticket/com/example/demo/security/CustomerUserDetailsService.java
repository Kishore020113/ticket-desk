package ticket.com.example.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ticket.com.example.demo.User;
import ticket.com.example.demo.repository.UserRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService{
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("UserName not found " + username));

        return org.springframework.security.core.userdetails.User.builder().
                username(user.getEmail()).
                password(user.getPassword()).
                authorities(List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))).
                build();
    }
}
