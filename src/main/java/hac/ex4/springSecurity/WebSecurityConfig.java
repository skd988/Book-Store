package hac.ex4.springSecurity;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Web Security configuration, defines the users of the website and the authorization and login pages.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Encodes passwords of the users.
     */
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * Configures users:
     * Admin:
     * user = admin
     * password = password
     * Users:
     * User1:
     * user = user1
     * password = user
     * User2:
     * user = user2
     * password = user
     * User3:
     * user = user3
     * password = user
     * @param auth Authorization builder
     * @throws Exception In case authorization fails.
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("password")).roles("ADMIN")
                .and()
                .withUser("user1").password(encoder.encode("user")).roles("USER")
                .and()
                .withUser("user2").password(encoder.encode("user")).roles("USER")
                .and()
                .withUser("user3").password(encoder.encode("user")).roles("USER");
    }

    /**
     * Configures pages under requirement to log in.
     * Authorize admin only to access admin pages,
     * and require all users to log in before making payment.
     * @param http Http security.
     * @throws Exception In case authorization fails.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/pay").hasAnyRole("USER", "ADMIN")

                .and()

                .formLogin()

                .and()
                .httpBasic()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll();
    }
}