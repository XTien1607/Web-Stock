package ltgroup.com.vn.webstock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {
  @Bean
  public UserDetailsService userDetailsService() {
    return new UserDetailServiceConfig();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder
        = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

    authenticationManagerBuilder
        .userDetailsService(userDetailsService())
        .passwordEncoder(passwordEncoder());

    AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
    httpSecurity.authorizeHttpRequests(request ->
            request.anyRequest().authenticated())
        .authenticationManager(authenticationManager);
    httpSecurity.csrf(AbstractHttpConfigurer::disable);

    return httpSecurity.build();
  }
}
