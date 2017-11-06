package am.test.security.confing;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    public static final SimpleGrantedAuthority AUTHORITY_ADMIN = new SimpleGrantedAuthority("ROLE_" + WebSecurityConfig.ROLE_ADMIN);
    public static final SimpleGrantedAuthority AUTHORITY_USER  = new SimpleGrantedAuthority("ROLE_" + WebSecurityConfig.ROLE_USER);

    //Authentication is about who somebody is.
    //Authorisation is about what they're allowed to do.

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password("pass")
                .roles(ROLE_ADMIN, ROLE_USER)
                .and()
                .withUser("user")
                .password("pass")
                .roles(ROLE_USER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/settings/**").hasAnyRole(ROLE_USER, ROLE_ADMIN)
                .antMatchers("/admin/users/**").hasAnyRole(ROLE_ADMIN)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/redirect")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }
}