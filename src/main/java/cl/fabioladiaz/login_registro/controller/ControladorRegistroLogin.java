package cl.fabioladiaz.login_registro.controller;
import cl.fabioladiaz.login_registro.model.Login;
import cl.fabioladiaz.login_registro.model.Usuario;
import cl.fabioladiaz.login_registro.service.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
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
    public String mostrarFormulario(Model modelo, HttpSession session){
        session.invalidate();
        modelo.addAttribute("usuario", new Usuario());
        modelo.addAttribute("usuarioLogin", new Login());
        return "index.jsp";
    }

    @GetMapping("inicio")
    public String mostrarFormulario(HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/";
        }
        return "inicio.jsp";
    }

    @PostMapping("procesa/registro")
    public String procesarRegistro(@Valid @ModelAttribute("usuario") Usuario usuario,
                                    BindingResult validaciones,
                                    @ModelAttribute("usuarioLogin") Login usuarioLogin,
                                    HttpSession session, 
                                    Model modelo){
        validaciones = this.servicioUsuario.validarRegistro(validaciones, usuario);
        if(validaciones.hasErrors()) {
            modelo.addAttribute("usuarioLogin", usuarioLogin);
            return "index.jsp";
        }
        Usuario usuarioCreado = servicioUsuario.crearUsuario(usuario);
        session.setAttribute("usuario", usuarioCreado);
        return "redirect:/inicio";
    }

    @PostMapping("procesa/login")
    public String procesarLogin(@Valid @ModelAttribute("usuarioLogin") Login usuarioLogin,
                                BindingResult validaciones, 
                                HttpSession session,
                                Model modelo){
        Usuario usuarioExistente = this.servicioUsuario.obtenerUsuarioPorNombreUsuario(usuarioLogin.getUsuarioLogin());
        validaciones = this.servicioUsuario.validarLogin(validaciones, usuarioLogin);
        if(validaciones.hasErrors()) {
            modelo.addAttribute("usuario", new Usuario());
            return "index.jsp";
        }
        session.setAttribute("usuario", usuarioExistente);
        return "redirect:/inicio";
    }
}
