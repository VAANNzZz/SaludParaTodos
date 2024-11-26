package com.exe.app.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    // Configuración de la seguridad HTTP
    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
             .csrf(csrf -> csrf.disable())
                         .authorizeHttpRequests(auth -> auth
                         .requestMatchers(HttpMethod.POST, "/email/send").permitAll()
                        // Acceso público
                        .requestMatchers("/register", "/login", "/static/**", "/public/**", "/Logo.png", "/resurces", "/Agregarpersona", "/templates", "/templates/**", "/olvidecontraseña", "/getPersona").permitAll()
                        // Acceso solo para administradores
                        .requestMatchers("/admin/**", "/personas", "/canalOrientadores", "/historial").hasRole("administrador")
                        // Acceso solo para usuarios
                        .requestMatchers("/user/**", "/citas", "/agregarCita", "/historial").hasRole("user")
                        // Acceso general para usuarios autenticados
                        .anyRequest()
                        )
            .formLogin(form -> form
                .defaultSuccessUrl("/index", true)  // Redirige a /index en caso de éxito
                .failureUrl("/login?error=true")   // Redirige a login con error
                .loginPage("/login")               // Página de login personalizada
                .permitAll())                      // Permite acceso a la página de login para todos
                .logout(logout -> logout
                    .logoutUrl("/logout")             // Ruta para cerrar sesión
                    .logoutSuccessUrl("/login?logout=true")  // Redirige a login con mensaje de éxito
                    .permitAll())

                    .exceptionHandling(Exeption -> Exeption
                    .accessDeniedPage("/permisos"))
                                         // Permite acceso al cierre de sesión para todos
            .build(); // Construye la configuración de seguridad // Construye la configuración de seguridad
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 

}
