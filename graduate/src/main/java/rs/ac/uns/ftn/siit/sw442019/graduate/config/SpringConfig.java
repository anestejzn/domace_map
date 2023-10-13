package rs.ac.uns.ftn.siit.sw442019.graduate.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rs.ac.uns.ftn.siit.sw442019.graduate.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf().disable().cors().and()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
               .authorizeHttpRequests()
               .requestMatchers("/auth/**")
               .permitAll()
               .requestMatchers("/users/register")
               .permitAll()
               .requestMatchers("/users/activate-account")
               .permitAll()
               .requestMatchers("/verify/**")
               .permitAll()
               .requestMatchers("/ws/**")
               .permitAll()
               .anyRequest().authenticated()
               .and()
               .authenticationProvider(authenticationProvider)
               .httpBasic()
               .and()
               .exceptionHandling();

       return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/auth/login");
    }

}
