package com.exe.app.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exe.app.repository.PersonaRepository;
import com.exe.app.services.CitaService;
import com.exe.app.services.PersonaDTO;
import com.exe.app.services.PersonaService;
import com.exe.app.services.RolService;

@Controller
public class Rutas {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private RolService rolService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // este metodo muestra el inicio
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Ruta de logout
    @GetMapping("/logout")
    public String logout() {
        // Este método solo se llama para confirmar el logout, pero no es necesario
        // porque Spring Security lo maneja automáticamente
        return "redirect:/login?logout=true"; // Redirige a la página de login con mensaje
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, RedirectAttributes redirectAttributes) {
        // Si hay algún mensaje flash, lo mostramos en la vista
        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) {
        // Lógica para verificar credenciales, si son correctas:
        redirectAttributes.addFlashAttribute("successMessage", "¡Bienvenido de nuevo, " + username + "!");
        return "redirect:/login"; // Redirige de nuevo a la página de login
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("persona", new Persona());
        model.addAttribute("roles", rolService.getRol());
        return "register";
    }

    @PostMapping("/register")
    public String registerPersona(@ModelAttribute("persona") Persona persona, RedirectAttributes redirectAttributes,
            Model model) {
        if (personaRepository.findByNumeroDocumento(persona.getNumeroDocumento()).isPresent()) {
            model.addAttribute("error", "El número de documento ya está en uso.");
            return "register";
        }

        // Guarda la persona
        personaRepository.save(persona);

        redirectAttributes.addFlashAttribute("successMessage", "¡Registro exitoso!");
        return "redirect:/login"; // Redirige al login después de un registro exitoso
    }

    @GetMapping("/olvidecontraseña")
    public String olvidecontraseña() {
        return "olvidecontraseña";
    }

    // este metodo muestra las personas registradas
    @GetMapping("/personas/aprendices")
    public String mostrarPersonas(Model model) {
        List<Persona> personas = personaService.getPersona();
        model.addAttribute("personas", personas);
        return "listaPersonaAprendices";
    }

    @GetMapping("/personas/psicosociales")
    public String mostraPsicosociales(Model model) {
        List<Persona> personas = personaService.getPersona();
        model.addAttribute("personas", personas);
        return "listaPersonaPsicosociales";
    }

    // este metodo brinda informacion de la persona en base a su email
    @RequestMapping(value = "getPersona", method = RequestMethod.GET)
    public @ResponseBody Persona getPersona(@RequestParam("email") String email) {
        return personaService.getByEmail(email).orElse(null);
    }

    @PostMapping("olvideContraseña")
    public String olvideContraseña(@RequestParam("email") String email) {
        Persona persona = getPersona(email);

        return "";
    }

    @GetMapping("/restablecerContraseña/{idPersonas}")
    public String restablecerContraseña(@ModelAttribute("idPersonas") Long idPersonas, ModelMap model) {
        Optional<Persona> persona = personaService.getPersonaById(idPersonas);
        model.addAttribute("persona", persona.get());
        return "restablecerContraseña";
    }

    @PostMapping("/resetpassword")
    public String restablecerContraseñapost(@RequestBody PersonaDTO req) {
        Optional<Persona> persona = personaService.getPersonaById(req.getIdPersonas());
        if (persona.isPresent()) {
            Persona p = persona.get();
            p.setContraseña(passwordEncoder.encode(req.getContraseña()));
            personaService.saveOrUpdate(p);
        }
        return "/restablecerContraseña";
    }

    // este metodo captura y muestra la informacion a editar
    @GetMapping("/AgregarPersona")
    public String AgregarPersona(ModelMap model) {
        model.addAttribute("persona", new Persona());
        model.addAttribute("roles", rolService.getRol());
        return "Agregarpersona";
    }

    // este metodo Guarda la informacion en la BD
    @PostMapping("/Agregarpersona")
    public String savePersona(@ModelAttribute("persona") Persona persona) {
        personaService.saveOrUpdate(persona);
        System.out.println("Se registró la Persona exitosamente!" + persona);
        return "redirect:/personas/aprendices";
    }

    // Método para editar persona
    @GetMapping("/editaraprendiz/{idPersonas}")
    public String editarPersona(@PathVariable("idPersonas") Long idPersonas, ModelMap Model) {
        Model.addAttribute("persona", new Persona());
        Optional<Persona> personas = personaService.getPersonaById(idPersonas);
        Model.addAttribute("persona", personas.orElse(null));
        List<Rol> roles = rolService.getRol();
        Model.addAttribute("roles", roles);
        return "/editarAprendiz";
    }

    // Método para actualizar persona
    @PostMapping("/editarAprendiz")
    public String metodoEditarPersona(PersonaDTO personaDTO) {
        personaService.updatePersona(personaDTO);

        return "redirect:/personas/aprendices";
    }

    @GetMapping("/editarpsicosocial/{idPersonas}")
    public String editarPsicosocial(@PathVariable("idPersonas") Long idPersonas, ModelMap Model) {
        Model.addAttribute("persona", new Persona());
        Optional<Persona> personas = personaService.getPersonaById(idPersonas);
        Model.addAttribute("persona", personas.orElse(null));
        List<Rol> roles = rolService.getRol();
        Model.addAttribute("roles", roles);
        return "/editarPsicosocial";
    }

    @PostMapping("/editarPsicosocial")
    public String metodoEditarPsicosocial(PersonaDTO personaDTO) {
        personaService.updatePersona(personaDTO);

        return "redirect:/personas/psicosociales";
    }

    // Eliminar
    @GetMapping("/eliminarpersona/{idPersonas}")
    public String eliminarpersona(Model model, @PathVariable Long idPersonas) {
        personaService.eliminarpersona(idPersonas);
        return "redirect:/personas/aprendices";
    }

    // mostrar roles
    @GetMapping("/listaRol")
    public String mostrarRoles(Model model) {
        List<Rol> rol = rolService.getRol();
        model.addAttribute("roles", rol);
        return "/listaRol";
    }

    @GetMapping("/listaRol/agregarRol")
    public String AgregarRoles(ModelMap model) {
        model.addAttribute("rol", new Rol());
        return "/AgregarRol";
    }

    @PostMapping("/listaRol/AgregarRol")
    public String saveRol(@ModelAttribute("rol") Rol rol) {
        rolService.saveOrUpdate(rol);
        System.out.println("Se registró la Persona exitosamente!" + rol);
        return "redirect:/listaRol";
    }

    @Autowired
    CitaService citaService;

    @GetMapping("/citas")
    public String mostrarCitas(Model model) {
        List<Cita> cita = citaService.getCita();
        model.addAttribute("citas", cita);
        return "/listaCita";
    }

    // este metodo captura y muestra la informacion a editar
    @GetMapping("/agregarCita")
    public String agregarCita(ModelMap model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("personas", personaService.getPersona()); // Lista de personas para llenar el formulario
        return "AgregarCita";
    }

    @PostMapping("/agregarCita")
    public String saveCita(@ModelAttribute("cita") Cita cita, @RequestParam("numeroDocumento") String numeroDocumento) {
        // Buscar la persona por número de documento usando el método con Optional
        Optional<Persona> optionalPersona = personaService.obtenerPersonaPorNumeroDocumento(numeroDocumento);

        if (optionalPersona.isPresent()) {
            // Asociar la persona a la cita si existe
            cita.setPersona(optionalPersona.get());
            citaService.saveOrUpdate(cita);
            System.out.println("Cita registrada exitosamente: " + cita);
        } else {
            System.out.println("No se encontró una persona con el número de documento: " + numeroDocumento);
            // Manejar el caso donde no se encuentra la persona
            return "redirect:/agregarCita?error=No se encontró una persona con ese número de documento";
        }

        return "redirect:/citas";
    }

    // Mostrar el formulario de edición de cita
    @GetMapping("/editarcita/{idCita}")
    public String editarCita(@PathVariable("idCita") Long idCita, ModelMap model) {
        Optional<Cita> optionalCita = citaService.getCitaById(idCita);

        if (optionalCita.isPresent()) {
            Cita cita = optionalCita.get();
            model.addAttribute("cita", cita); // Agregar la cita encontrada al modelo
            model.addAttribute("personas", personaService.getPersona()); // Lista de personas
            return "/editarcita"; // Retorna la vista del formulario de edición
        } else {
            System.out.println("No se encontró una cita con el ID: " + idCita);
            return "redirect:/citas?error=No se encontró la cita"; // Redirige con un mensaje de error si no se
                                                                   // encuentra la cita
        }
    }

    // Guardar los cambios de la cita editada
@PostMapping("/editarcita")
public String actualizarCita(@ModelAttribute("cita") Cita cita, @RequestParam("numeroDocumento") String numeroDocumento) {
    // Buscar la persona asociada por número de documento
    Optional<Persona> optionalPersona = personaService.obtenerPersonaPorNumeroDocumento(numeroDocumento);

    if (optionalPersona.isPresent()) {
        // Asociar la persona a la cita y guardar
        cita.setPersona(optionalPersona.get());
        citaService.saveOrUpdate(cita);
        System.out.println("Cita actualizada exitosamente: " + cita);
    } else {
        System.out.println("No se encontró una persona con el número de documento: " + numeroDocumento);
        return "redirect:/editarCita/" + cita.getIdCitas() + "?error=No se encontró una persona con ese número de documento";
    }

    return "redirect:citas";
}

    @GetMapping("/eliminarcita/{idCitas}")
    public String eliminarcita(Model model, @PathVariable Long idCitas) {
        citaService.eliminarcita(idCitas);
        return "redirect:/citas";
    }

    @GetMapping("/canalOrientadores")
    public String mostrarCanalOrientadores(Model model) {
        return "canalOrientadores";
    }
}
