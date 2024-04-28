package ltgroup.com.vn.webstock.config;

import lombok.RequiredArgsConstructor;
import ltgroup.com.vn.webstock.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserDetailServiceConfig implements UserDetailsService {
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userRepository.findByUserName(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Could not find username");
    }
    return new User(
        user.get().getUserName(),
        user.get().getPassword(),
        List.of(new SimpleGrantedAuthority(user.get().getRole())));
  }
}