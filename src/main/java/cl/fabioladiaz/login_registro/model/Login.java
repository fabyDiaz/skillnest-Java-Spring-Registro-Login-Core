package cl.fabioladiaz.login_registro.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Login {
    @NotBlank(message = "Por favor proporciona el nombre de usuario.")
    @Size(min = 3, max = 15, message = "El nombre de usuario debe de contener entre 3 y 15 caracteres.")
	private String usuarioLogin;
	
    @NotBlank(message = "Por favor proporciona la constraseña.")
    @Size(min = 8, message = "El password necesita tener al menos 8 catacteres.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "El password necesita incluir al menos una letra mayúscula, una letra minúscula y un número")
	private String contrasenaLogin;
	
	public Login() {}
	public String getUsuarioLogin() {
		return usuarioLogin;
	}
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}
	public String getContrasenaLogin() {
		return contrasenaLogin;
	}
	public void setContrasenaLogin(String contrasenaLogin) {
		this.contrasenaLogin = contrasenaLogin;
	}
}
