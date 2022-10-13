package nemchikov.firstSecurity.config;

import nemchikov.firstSecurity.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

//    private final PersonDetailsService personDetailsService;
//
//    @Autowired
//    public SecurityConfig(PersonDetailsService personDetailsService) {
//        this.personDetailsService = personDetailsService;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Конфигурируем сам Spring Security
        // Конфигурируем авторизацию
        http.csrf().disable()  // Отключаем защиту от межсайтовой подделки запросов
                .authorizeRequests()
                .antMatchers("/auth/login", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error");
        return http.build();
    }

    // Настраивает аутентификацию
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(personDetailsService);
//    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
