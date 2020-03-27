package blackapple.webbook.userPart.security;

import blackapple.webbook.userPart.service.UserDetailsServiceImpl;
import blackapple.webbook.userPart.security.jwt.AuthEntryPointJwt;
import blackapple.webbook.userPart.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String loginEndpoint = "/api/auth/**";
    private static final String userEndpoint = "/api/user/**";
    private static final String bookEndpoint = "/api/book/**";
    private static final String orderEndpoint = "/api/order/**";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and()
                .csrf().disable() // настройка CORS и CSRF
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler) // настройка выброса исключений
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // настройка управления сеансами
                .and()
                .authorizeRequests()
                .antMatchers(loginEndpoint).permitAll()
                .antMatchers(bookEndpoint).permitAll()
                .antMatchers(orderEndpoint).permitAll()
                .antMatchers(userEndpoint).permitAll().anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
