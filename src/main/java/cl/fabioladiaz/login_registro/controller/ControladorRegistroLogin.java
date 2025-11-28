package cl.fabioladiaz.login_registro.controller;
import cl.fabioladiaz.login_registro.model.Login;
import cl.fabioladiaz.login_registro.model.Usuario;
import cl.fabioladiaz.login_registro.service.ServicioUsuario;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControladorRegistroLogin {

    @Autowired
    ServicioUsuario servicioUsuario;

    @GetMapping()
    public String mostrarFormulario(Model modelo){
        modelo.addAttribute("usuario", new Usuario());
        modelo.addAttribute("usuarioLogin", new Login());
        return "index.jsp";
    }

    @GetMapping("inicio")
    public String mostrarFormulario(){
        return "inicio.jsp";
    }

    @PostMapping("procesa/registro")
    public String procesarRegistro(@Valid @ModelAttribute("usuario") Usuario usuario,
                                    BindingResult validaciones,
                                    @ModelAttribute("usuarioLogin") Login usuarioLogin, 
                                    Model modelo){
        
        validaciones = this.servicioUsuario.validarRegistro(validaciones, usuario);
        if(validaciones.hasErrors()) {
            modelo.addAttribute("usuarioLogin", usuarioLogin);
            return "index.jsp";
        }
        servicioUsuario.crearUsuario(usuario);
        return "redirect:/inicio";
    }

    @PostMapping("procesa/login")
    public String procesarLogin(@Valid @ModelAttribute("usuarioLogin") Login usuarLogin,
                                BindingResult validaciones, 
                                Model modelo){
        Usuario usuarioExistente = this.servicioUsuario.obtenerUsuarioPorNombreYContrasena(usuarLogin);        
        if(usuarioExistente == null){
            this.servicioUsuario.validarLogin(validaciones, usuarLogin);
        }
        if(validaciones.hasErrors()) {
            modelo.addAttribute("usuario", new Usuario());
            return "index.jsp";
        }

        return "redirect:/inicio";
    }
}
