package com.eam.prueba.entities;

import com.eam.prueba.enumitem.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor   //Genera el constructor x default (sin argumentos) mediante Lombok
@Getter                     // Genera los Getters completos mediante Lombok
@Setter                     // Genera los Setters completos mediante Lombok
@Entity
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;
    private String mail;
    private String pass;

    @Enumerated(EnumType.STRING)
    private Rol rol;

}
