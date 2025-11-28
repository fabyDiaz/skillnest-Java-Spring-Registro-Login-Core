package cl.fabioladiaz.login_registro.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.fabioladiaz.login_registro.model.Usuario;

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long>{

    // Buscar por nombre de usuario
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

}
