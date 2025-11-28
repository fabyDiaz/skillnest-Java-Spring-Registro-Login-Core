package cl.fabioladiaz.login_registro.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import cl.fabioladiaz.login_registro.model.Login;
import cl.fabioladiaz.login_registro.model.Usuario;
import cl.fabioladiaz.login_registro.repository.RepositorioUsuario;

@Service
public class ServicioUsuario {
    
    @Autowired
    RepositorioUsuario repositorioUsuario;

     public Usuario obetenerUsuarioPorId(Long id){
        return this.repositorioUsuario.findById(id).orElse(null);
    }

    public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario){
        return this.repositorioUsuario.findByNombreUsuario(nombreUsuario).orElse(null);
    }

    public Usuario crearUsuario(Usuario usuario){
        String newContrasena = BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt());
        usuario.setContrasena(newContrasena);
        return this.repositorioUsuario.save(usuario);
    }

    public Usuario actualizarUsuario(Usuario usuario){
        return this.repositorioUsuario.save(usuario);
    }

    public void eliminarUsuario(Long id){
        this.repositorioUsuario.deleteById(id);
    }

    public BindingResult validarRegistro(BindingResult validaciones, Usuario usuarioRegistrado){
        
        Usuario usuarioExistente = this.obtenerUsuarioPorNombreUsuario(usuarioRegistrado.getNombreUsuario());
        if(usuarioExistente != null){
        validaciones.rejectValue("nombreUsuario", "UsuarioExistente", 
                                "Este usuario ya está registrado.");
        }
        
        if(!usuarioRegistrado.getContrasena().equals(usuarioRegistrado.getConfirmacionContrasena())){
            validaciones.rejectValue("confirmacionContrasena", "passwordNoCoincide", 
                                "Las contraseñas deben ser iguales.");
        }
        return validaciones;
    }

    public BindingResult validarLogin(BindingResult validaciones, Login usuarioLogin){
        Usuario usuarioDB = this.obtenerUsuarioPorNombreUsuario(usuarioLogin.getUsuarioLogin());
        /*if(!usuarioDB.getContrasena().equals(usuarioLogin.getContrasenaLogin())){
            validaciones.rejectValue("confirmarPassword","passwordNoCoincide", "Las contraseñas deben de ser iguales.");
        }*/
        if (usuarioDB == null) {
             validaciones.rejectValue("contrasenaLogin", "credencialesInvalidas", 
                                "Credenciales inválidas.");
            return validaciones;
        }
        if(!BCrypt.checkpw(usuarioLogin.getContrasenaLogin(), usuarioDB.getContrasena())){
             validaciones.rejectValue("contrasenaLogin", "credencialesInvalidas", 
                                "Credenciales inválidas.");
        }  
        return validaciones;
    }







}
