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
import com.exe.app.services.HistorialPsicosocialService;
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
    

    //este metodo muestra el inicio
    @GetMapping("/index")
    public String index(){
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
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("persona", new Persona());
        model.addAttribute("roles", rolService.getRol());
        return "register"; 
    }

    @PostMapping("/register")
    public String registerPersona(@ModelAttribute("persona") Persona persona, Model model) {
        // Verifica si el correo ya está registrado
        if (personaRepository.findByNumeroDocumento(persona.getNumeroDocumento()).isPresent()) {
            model.addAttribute("error", "El numero de documento ya está en uso.");
            return "register"; 
        }
        
        // Cifra la contraseña antes  guardar
        persona.setContraseña(passwordEncoder.encode(persona.getContraseña()));
        personaService.saveOrUpdate(persona); // Guarda el usuario en la base de datos
        return "redirect:/login"; 
    }

    @GetMapping("/olvidecontraseña")
    public String olvidecontraseña(){
        return "olvidecontraseña";
    }
    

    //este metodo muestra las personas registradas
    @GetMapping("/personas")
    public String mostrarPersonas(Model model){
        List<Persona> personas = personaService.getPersona();
        model.addAttribute("personas", personas);
        return "listaPersona";
    }

    //este metodo brinda informacion de la persona en base a su email
    @RequestMapping(value="getPersona", method = RequestMethod.GET)
    public @ResponseBody Persona getPersona(@RequestParam("email") String email){
        return personaService.getByEmail(email).orElse(null);
    }

    @PostMapping("olvideContraseña")
    public String olvideContraseña(@RequestParam("email") String email){
        Persona persona = getPersona(email);
        
        return "";
    }

    @GetMapping("/restablecerContraseña/{idPersonas}")
    public String restablecerContraseña(@ModelAttribute("idPersonas") Long idPersonas, ModelMap model){
        Optional<Persona> persona = personaService.getPersonaById(idPersonas);
        model.addAttribute("persona", persona.get());
        return "restablecerContraseña";
    }

    @PostMapping("/resetpassword")
    public String restablecerContraseñapost(@RequestBody PersonaDTO req){
        Optional<Persona> persona = personaService.getPersonaById(req.getId());
		if (persona.isPresent()) {
			Persona p = persona.get();
			p.setContraseña(passwordEncoder.encode(req.getPassword()));
			personaService.saveOrUpdate(p);
		}
        return "/restablecerContraseña";
    }

    //este metodo captura y muestra la informacion a editar 
    @GetMapping("/AgregarPersona")
    public String AgregarPersona(ModelMap model){
        model.addAttribute("persona", new Persona());
        model.addAttribute("roles", rolService.getRol());
        return "Agregarpersona";
    }  

    //este metodo Guarda la informacion en la BD
    @PostMapping("/Agregarpersona")
    public String savePersona(@ModelAttribute("persona")Persona persona) {
        personaService.saveOrUpdate(persona);
        System.out.println("Se registró la Persona exitosamente!" + persona);
        return "redirect:/personas";
    }

    //editarpersona
    @GetMapping("/editarpersona/{idPersonas}")
    public String editarPersona(@PathVariable("idPersonas") Long idPersonas,ModelMap Model){
    Model.addAttribute("persona", new Persona());   
    Optional<Persona> personas = personaService.getPersonaById(idPersonas);
    Model.addAttribute("persona", personas.orElse(null));
    List<Rol> roles = rolService.getRol();
    Model.addAttribute("roles", roles);
    return "/editarpersona";
    }


    //editarpersona
    @PostMapping("/editarPersona/editarPersona")
    public String metodoEditarPersona(@ModelAttribute("persona") Persona persona){
        personaService.saveOrUpdate(persona);
        return "redirect:/personas";
    }

    //Eliminar
    @GetMapping("/eliminarpersona/{idPersonas}")
    public String eliminarpersona(Model model, @PathVariable Long idPersonas){
        personaService.eliminarpersona(idPersonas);
        return "redirect:/personas";
    }

    //mostrar roles
    @GetMapping("/rol")
    public String mostrarRoles(Model model){
       List<Rol> rol = rolService.getRol();
        model.addAttribute("roles", rol);
       return "/listaRol";
    }

    @GetMapping("/agregarRol")
    public String AgregarRoles(ModelMap model){
        model.addAttribute("rol", new Rol());
        return "/agregarRol";
    }

    @PostMapping("/AgregarRol")
    public String saveRol(@ModelAttribute("rol")Rol rol) {
        rolService.saveOrUpdate(rol);
        System.out.println("Se registró la Persona exitosamente!" + rol);
        return "redirect:/rol";
    }

    @Autowired
    CitaService citaService;
    @GetMapping("/citas")
    public String mostrarCitas(Model model){
       List<Cita> cita = citaService.getCita();
        model.addAttribute("citas", cita);
       return "/listaCita";
    }

    //este metodo captura y muestra la informacion a editar 
    @GetMapping("/agregarCita")
public String agregarCita(ModelMap model) {
    model.addAttribute("cita", new Cita());
    model.addAttribute("historial_psicosocial", historialPsicosocialService.gethistorialPsicosocial()); // Asegúrate de tener este método
    model.addAttribute("personas", personaService.getPersona()); // Asegúrate de tener este método
    return "AgregarCita";
}

    //este metodo Guarda la informacion en la BD
    @PostMapping("/agregarCita")
    public String saveCita(@ModelAttribute("cita")Cita cita) {
        citaService.saveOrUpdate(cita);
        System.out.println("Se registró la Persona exitosamente!" + cita);
        return "redirect:/citas";
    }

    @GetMapping("/eliminarcita/{idCitas}")
    public String eliminarcita(Model model, @PathVariable Long idCitas){
        citaService.eliminarcita(idCitas);
        return "redirect:/citas";
    }

    @Autowired
    HistorialPsicosocialService historialPsicosocialService;

    @GetMapping("/historial")
    public String mostrarHistorialPsicosocial(Model model) {
    List<HistorialPsicosocial> historialPsicosocial = historialPsicosocialService.gethistorialPsicosocial();
    model.addAttribute("historial_psicosocial", historialPsicosocial);
    return "/listahistorialpsicosocial";
    }

    @GetMapping("/eliminarhistorialPsicosocial/{idHistorial_Psicosocial}")
    public String eliminarhistorialpsicosocial(@PathVariable("idHistorial_Psicosocial") Long idHistorial_Psicosocial, RedirectAttributes redirectAttributes) {
    historialPsicosocialService.eliminarhistorialpsicosocial(idHistorial_Psicosocial);
    redirectAttributes.addFlashAttribute("mensaje", "historial psicosocial eliminada con éxito");
    return "redirect:/historial";
    }

    @GetMapping("/canalAtencion")
    public String mostrarCanalAtencion(Model model) {
        // Aquí puedes agregar atributos al modelo si es necesario
        // model.addAttribute("atributo", valor);
        
        // Devuelve el nombre de la vista que quieres mostrar
        return "canalAtencion"; // Asegúrate de que esta vista exista en tu carpeta de plantillas
    }

    @GetMapping("/canalAprendices")
    public String mostrarCanalAprendices(Model model) {
        return "canalAprendices"; 
    }

    @GetMapping("/canalOrientadores")
    public String mostrarCanalOrientadores(Model model) {
        return "canalOrientadores"; 
    }

    @GetMapping("/canalOtros")
    public String mostrarCanalOtros(Model model) {
        return "canalOtros";
    }

    @GetMapping("/tareasAprendices")
    public String mostrarTareasAprendices(Model model){
        return "tareasAprendices";
    }

    @GetMapping("/tareasOrientadores")
    public String mostrarTareasOrientadores (Model model){
        return "tareasOrientadores";
    }
}
