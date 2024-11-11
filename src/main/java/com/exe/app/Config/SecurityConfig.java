package com.exe.app.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
            .authorizeHttpRequests(auth -> auth // Controla las rutas que se permiten sin autenticar y cuáles están protegidas
                .requestMatchers("/register", "/login", "/static/**", "/public/**", "/LOGO SALUD PARA TODOS.jpg",
                    "/templates/**", "/resurces", "/AgregarPersona", "/Agregarpersona", "/templates")
                .permitAll() 
                .requestMatchers("/v1/index").authenticated() // Solo autenticados pueden acceder a /v1/index
                .requestMatchers("/v1/administrador").hasAuthority("administrador") // Solo admin puede acceder a /v1/administrador
                .anyRequest().authenticated())  // Requiere autenticación para cualquier otra ruta
            .formLogin(form -> form
                .defaultSuccessUrl("/index", true)  // Redirige a /index en caso de éxito
                .failureUrl("/login?error=true")   // Redirige a login con error
                .loginPage("/login")               // Página de login personalizada
                .permitAll())                      // Permite acceso a la página de login para todos
            .build(); // Construye la configuración de seguridad // Construye la configuración de seguridad
    }

    // Definimos un UserDetailsService para proporcionar los usuarios en memoria
    /* @Bean
    public UserDetailsService userDetailsService() {
        // Creamos dos usuarios en memoria para pruebas
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("1111") // Nombre de usuario
                .password("1111") // Contraseña del usuario
                .roles("USER") // Rol del usuario
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("1234") // Nombre de usuario admin
                .password("1234") // Contraseña del admin
                .roles("ADMIN") // Rol del administrador
                .build();

        // Devolvemos un InMemoryUserDetailsManager con los usuarios creados
        return new InMemoryUserDetailsManager(user, admin);
    } */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 

}
