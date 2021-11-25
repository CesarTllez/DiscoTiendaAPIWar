/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author cesar
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.unicundi.discotiendaapiwar.CORS.CorsFilter.class);
        resources.add(co.edu.unicundi.discotiendaapiwar.controlador.ArtistaControlador.class);
        resources.add(co.edu.unicundi.discotiendaapiwar.controlador.CancionControlador.class);
        resources.add(co.edu.unicundi.discotiendaapiwar.controlador.DiscoController.class);
        resources.add(co.edu.unicundi.discotiendaapiwar.controlador.FormatoControlador.class);
        resources.add(co.edu.unicundi.discotiendaapiwar.controlador.RolControlador.class);
        resources.add(co.edu.unicundi.discotiendaapiwar.controlador.SesionControlador.class);
        resources.add(co.edu.unicundi.discotiendaapiwar.controlador.UsuarioControlador.class);
        resources.add(co.edu.unicundi.discotiendaapiwar.excepciones.ExceptionHandler.class);
        resources.add(co.edu.unicundi.discotiendaapiwar.interceptor.Interceptor.class);
    }
    
}
