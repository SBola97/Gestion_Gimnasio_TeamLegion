package com.GestionGimnasio.tesisgestiongimnasio.seguridad;

import net.bytebuddy.asm.Advice;
import org.mapstruct.BeanMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    private CustomUserService userDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {

        /*
        http
                .authorizeRequests().antMatchers("/*.css", "/*.js", "/svgs/**").permitAll()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/gym/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();*/
        /*
        http
                .authorizeRequests().antMatchers("/*.css", "/*.js", "/svgs/**").permitAll()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/gym/auth/**").permitAll();

        return http.build();

         */

        http
                .authorizeRequests().antMatchers("/*.css", "/*.js", "/svgs/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/autenticar")
                .usernameParameter("name").passwordParameter("contraseña")
                .defaultSuccessUrl("/index",true).permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");
        return http.build();
    }
/*
    @Bean
    public UserDetailsService users()
    {
        UserDetails admin = User.builder()
                .username("admin")
                .password("111")
                .roles("Admin")
                .build();
        UserDetails client = User.builder()
                .username("client")
                .password("222")
                .roles("Cliente")
                .build();
        return new InMemoryUserDetailsManager(admin, client);
    }*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
