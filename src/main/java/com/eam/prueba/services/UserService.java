package com.eam.prueba.services;

import com.eam.prueba.entities.Usuario;
import com.eam.prueba.enumitem.Rol;
import com.eam.prueba.exception.MyException;
import com.eam.prueba.repository.UserRepo;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Resuelve la lógica de la aplicación; contiene los algoritmos, validaciones y coordinación necesaria para
 * resolver la problemática del negocio - Service Object
 *  
 * @Autowired 
 * Permite resolver la inyección de dependencias e instanciarlas automáticamente, al momento de ser 
 * utilizadas por la lógica del negocio.
 * 
 * @Transactional
 * Es una anotación de Spring que nos permite realizar modificaciones de las persistencias de la DB; aunque
 * para reslizar solo consultas, es posible tambien mapearla de la siguiente manera:
 * @Transactional(readOnly=true) "solo lectura"
 */

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public void registrar(String name, String mail, String pass, String pass2) throws MyException {

        validacion(name, mail, pass, pass2);

        Usuario usuario = new Usuario();

        usuario.setName(name);
        usuario.setMail(mail);
        usuario.setPass(pass);
        usuario.setRol(Rol.USER);   // Se otorga x default rol de usuario

        userRepo.save(usuario);
    }

    private void validacion(String name, String mail, String pass, String pass2) throws MyException {

        if (name.isEmpty() || name == null) {
            throw new MyException("El nombre de usuario no puede ser nulo o estar vacío.");
        }
        if (mail.isEmpty() || mail == null) {
            throw new MyException("El mail no puede ser nulo o estar vacío.");
        }
        if (pass.isEmpty() || pass == null || pass.length() <= 5) {
            throw new MyException("La clave no puede estar vacía y debe contener como mínimo, 5 caracteres");
        }
        if (!pass.equals(pass2)) {
            throw new MyException("Las claves ingresadas deben ser iguales");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario usuario = userRepo.buscarXMail(mail);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString()); // ROLE_USER

            permisos.add(p); // agregamos el rol a la lista de permisos

            return new User(usuario.getMail(), usuario.getPass(), permisos);
        } else {
            return null;
        }

    }
}
