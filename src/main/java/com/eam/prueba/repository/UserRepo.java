package com.eam.prueba.repository;

import com.eam.prueba.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Capa de Acceso a Datos (CAD), provee un API (Aplication Interfaz), que permite la comunicación entre diferentes aplicaciones
 * En nuestro caso, resuelve la comunicación entre el proyecto y la DB relacionada al objeto
 */

@Repository
public interface UserRepo extends JpaRepository<Usuario, String> {

    
    @Query("SELECT u FROM Usuario u WHERE u.mail = :mail")
    public Usuario buscarXMail(@Param("mail") String mail);
    
}
