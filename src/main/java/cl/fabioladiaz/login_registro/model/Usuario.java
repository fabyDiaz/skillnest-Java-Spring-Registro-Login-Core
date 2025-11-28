package cl.fabioladiaz.login_registro.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    @NotBlank(message = "Por favor proporciona el nombre de usuario.")
    @Size(min = 3, max = 15, message = "El nombre de usuario debe tener mínimo 3 y máximo 15 caracteres")
    @Column(unique = true)
    private String nombreUsuario; //Nombre del usuario.
    
    @NotBlank(message = "Por favor proporciona la constraseña.")
    @Size(min = 8, message = "El password necesita tener al menos 8 catacteres.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "El password necesita incluir al menos una letra mayúscula, una letra minúscula y un número")
    private String contrasena; 

    @Transient
    private String confirmacionContrasena;
    
    @NotBlank(message = "El correo es requerido.")
    @Email(message = "Por favor proporciona un correo válido.")
    private String correo;

    @NotBlank(message = "Por favor proporciona tu nombre")
    @Size(min = 3, message = "El nombre debe contener al menor 3 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", 
             message = "El nombre no debe contener números ni caracteres especiales")
    private String nombre; // Nombre real del usuario.
    
    @NotBlank(message = "Por favor proporciona tu apellido")
    @Size(min = 3, message = "El apellido debe contener al menos 3 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", 
             message = "El nombre no debe contener números ni caracteres especiales")
    private String apellido; 
    @Past(message = "Tu fecha de nacimiento necesita ser una fecha en el pasado.")
    private LocalDate fechaNacimiento;
    
    @Column(updatable =false)
    private LocalDateTime fechaCreacion; // Fecha en que se creó el registro en la base de datos.
    private LocalDateTime fechaActualizacion; //Fecha utilizada para mantener registro de actualización del usuario en la base de datos
    

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getConfirmacionContrasena() {
        return confirmacionContrasena;
    }

    public void setConfirmacionContrasena(String confirmacionContrasena) {
        this.confirmacionContrasena = confirmacionContrasena;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @PrePersist
    public void onCreate(){
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.fechaActualizacion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Registro [id=" + id + ", nombreUsuario=" + nombreUsuario + ", contrasena=" + contrasena
                + ", correo=" + correo + ", nombre=" + nombre
                + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", fechaCreacion=" + fechaCreacion
                + ", fechaActualizacion=" + fechaActualizacion + "]";
    }
 
}
