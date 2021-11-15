/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.controlador;

import co.edu.unicundi.discotiendaejbjar.entidad.Prueba;
import co.edu.unicundi.discotiendaejbjar.servicio.IPruebaServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author cesar
 */
@Stateless
@Path("prueba")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PruebaControlador {
    
    @EJB
    private IPruebaServicio servicio;
    
    @POST
    @Path("/obtener")
    public Response mostrar(Prueba object){
        this.servicio.mostrar(object);
        return Response
                .status(Response.Status.OK)
                .build();
    }
    
}
