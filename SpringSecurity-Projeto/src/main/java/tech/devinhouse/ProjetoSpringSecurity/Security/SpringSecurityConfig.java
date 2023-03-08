package tech.devinhouse.ProjetoSpringSecurity.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tech.devinhouse.ProjetoSpringSecurity.Service.usuarioService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private usuarioService usuarioService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http


                .authorizeRequests()
                //Define acesso para os colaboradores
                .antMatchers(HttpMethod.POST,"/usuario/**").hasAnyRole("ADMINISTRADOR","GERENTE","COLABORADOR")
                .antMatchers(HttpMethod.POST,"/farmacia/**").hasAnyRole("ADMINISTRADOR","GERENTE","COLABORADOR")
                .antMatchers(HttpMethod.POST,"/medicamentos/**").hasAnyRole("ADMINISTRADOR","GERENTE","COLABORADOR")
                .antMatchers(HttpMethod.GET,"/usuario/**").hasAnyRole("ADMINISTRADOR","GERENTE","COLABORADOR")
                .antMatchers(HttpMethod.GET,"/farmacia/**").hasAnyRole("ADMINISTRADOR","GERENTE","COLABORADOR")
                .antMatchers(HttpMethod.GET,"/medicamentos/**").hasAnyRole("ADMINISTRADOR","GERENTE","COLABORADOR")
                .antMatchers(HttpMethod.PUT,"/usuario/**").hasAnyRole("ADMINISTRADOR","GERENTE")
                .antMatchers(HttpMethod.PUT,"/farmacia/**").hasAnyRole("ADMINISTRADOR","GERENTE")
                .antMatchers(HttpMethod.PUT,"/medicamentos/**").hasAnyRole("ADMINISTRADOR","GERENTE")

                .antMatchers(HttpMethod.DELETE,"/usuario/**").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE,"/farmacia/**").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE,"/medicamentos/**").hasRole("ADMINISTRADOR")




                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtLoginFilter("/auth", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtApiAutenticaoFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(usuarioService)


                .passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/**.html",
                "/v2/api-docs",
                "/webjars/**");
    }
}
