/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.controlador;

import co.edu.unicundi.discotiendaejbjar.entidad.Formato;
import co.edu.unicundi.discotiendaejbjar.servicio.IFormatoServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase que proporciona los servicios del formato.
 * @author César Rodríguez
 * @author Eison Morales
 * @author Juan Páez
 * @author Diego Cobos
 */
@Stateless
@Path("formatos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormatoControlador {
    
    /**
     * Permite la conexión con el EJB para adquirir los servicios.
     */
    @EJB
    private IFormatoServicio servicio;
    
    /**
     * Método GET que permite buscar un formato por id.
     * @param id
     * @return Response
     */
    @GET
    @Path("/buscarPorId/{id}")
    public Response buscarPorId(@Valid @PathParam("id") Integer id){
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarPorId(id))
                .build();
    }
    
    /**
     * Método GET que permite buscar todos los formatos.
     * @return Response
     */
    @GET
    @Path("/buscarTodos")
    public Response buscarTodos(){
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarTodos())
                .build();
    }
    
    /**
     * Método POST que permite registrar un formato.
     * @param formato
     * @return Response
     */
    @POST
    @Path("/registrar")
    public Response registrar(@Valid Formato formato){
        this.servicio.registrar(formato);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }
    
}
