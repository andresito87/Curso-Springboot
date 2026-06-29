package dev.andrescoder.portfoliobackend.config;

import dev.andrescoder.portfoliobackend.service.interfaces.IUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Indica que esta clase sirve como configuración global de la aplicación
@EnableWebSecurity // Habilita la seguridad web en la aplicación, permitiendo configurar autenticación y autorización
@RequiredArgsConstructor // Genera un constructor con todos los atributos finales de la clase
public class WebSecurityConfig {

    private final IUserDetailsService userDetailsService;

    // @Bean Indica a Spring que para que la clase funcione, necesita crear e inyectar una instancia del bean. Es un
    // componente compartido, es decir, se crea 1 y se pone a disposición de cualquier otro componente de la aplicación
    // que lo necesite
    @Bean
    // SecurityFilterChain es una interfaz que define la cadena de filtros de seguridad que se aplican a las
    // solicitudes HTTP entrantes. Es el flujo de seguridad por defecto que se aplica en las peticiones http.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(auth -> auth.requestMatchers(
                                "/educations",
                                "/experiences",
                                "/skills",
                                "/projects",
                                "/personal-info"
                        ).authenticated() // Requiere autenticación para acceder a esas rutas
                        .requestMatchers(
                                "/educations/new",
                                "/educations/save",
                                "/educations/edit/**",
                                "/educations" + "/delete/**"
                        ).authenticated() // Requiere autenticación para acceder a esas rutas
                        .requestMatchers(
                                "/experiences/new",
                                "/experiences/save",
                                "/experiences/edit/**",
                                "/experiences" + "/delete/**"
                        ).authenticated() // Requiere autenticación para acceder a esas rutas
                        .requestMatchers("/skills/new", "/skills/save", "/skills/edit/**", "/skills" + "/delete/**")
                        .authenticated() // Requiere autenticación para acceder a esas rutas
                        .requestMatchers("/projects/new", "/projects/save", "/projects/edit/**", "/projects" +
                                "/delete/**")
                        .authenticated() // Requiere autenticación para acceder a esas rutas
                        .requestMatchers(
                                "/personal-info/new",
                                "/personal-info/save",
                                "/personal-info/edit/**",
                                "/personal-info" + "/delete/**"
                        ).authenticated() // Requiere autenticación para acceder a esas rutas
                        .anyRequest().permitAll()) // Y permitimos el acceso libre al resto de rutas
                .formLogin(form -> form.defaultSuccessUrl("/personal-info", true) // Redirige al hacer login correcto
                        .failureUrl("/login?error") // Redirige login param?error= al login con credencial incorrecta
                        .loginPage("/login").permitAll()) // Habilita el formulario de login
                .logout(logout -> logout.logoutUrl("/logout") // Habilita el logout en la ruta /logout
                        .logoutRequestMatcher(request ->
                                // Acepta POST para que funcionen los enlaces y los formularios utilizando la
                                // protección contra csrf con el token incrustado en el formulario
                                ("POST".equalsIgnoreCase(request.getMethod()) && "/logout".equals(request.getRequestURI()))) // Configura la ruta de logout
                        .logoutSuccessUrl("/login?logout") // Redirige a login con param?logout= al hacer logout
                        .invalidateHttpSession(true) // Invalida la sesión al hacer logout
                        .deleteCookies("JSESSIONID") // Elimina la cookie de sesión al hacer logout
                        // la ruta /logout es url pública para que el instante mientras el usuario hace logout,
                        // pueda acceder a la url sin estar autenticado. Si no fuera así Spring podría bloquear el
                        // proceso justo después de eliminar la cookie, impidiendo completar el proceso de logout
                        .permitAll());


        return http.build();
    }

    // Configura el servicio de detalles de usuario y el encriptador de contraseñas
    private void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // Crea encriptador de contraseñas para los usuarios registrados en el sistema
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}