package com.eam.prueba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Es la capa responzable de mediar entre la interfaz de usuario (vista) y la aplicación, es decir las capas del modelo
 * es parte integral del patron MVC (Model View Controller).
 * Contiene métodos para manejar peticiones HTTP
 * 
 * La anotación @RequestMapping genera el mapeo entre la ruta de la petición y el método del controlador.
 * inluye @GetMapping para solicitudes y @PostMapping para el envío de información
 */

@Controller
@RequestMapping("/")
public class PortalController {

    @GetMapping("/")
    public String index() { // localhost: 8080/

        return "index.html";
    }

    @GetMapping("/registro") // localhost:8080/registro
    public String registrar() {

        return "registro.html";
    }

    @GetMapping("/login") // localhost:8080/login
    public String login() {

        return "login.html";
    }
}
