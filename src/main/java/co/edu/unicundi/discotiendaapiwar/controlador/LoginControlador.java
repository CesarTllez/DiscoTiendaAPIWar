/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.controlador;

import co.edu.unicundi.discotiendaejbjar.servicio.IUsuarioServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase que proporciona el servicio de autenticación.
 * @author César Rodríguez
 * @author Eison Morales
 * @author Juan Páez
 * @author Diego Cobos
 */
@Stateless
@Path("autenticacion")
public class LoginControlador {
    
    /**
     * Permite la conexión con el EJB para adquirir los servicios.
     */
    @EJB
    private IUsuarioServicio servicio;
    
    /**
     * Método GET que permite obtener el token de autenticación.
     * @param apodo
     * @param contrasena
     * @return 
     */
    @GET
    @Path("/obtenerToken/{apodo}/{contrasena}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response iniciarSesion(@Valid @PathParam("apodo") String apodo, 
                                                               @PathParam("contrasena") String contrasena ){
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.login(apodo, contrasena))
                .build();
    }
    
}
