/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.controlador;

import co.edu.unicundi.discotiendaejbjar.entidad.Rol;
import co.edu.unicundi.discotiendaejbjar.excepciones.BussinessException;
import co.edu.unicundi.discotiendaejbjar.excepciones.EntityValidationException;
import co.edu.unicundi.discotiendaejbjar.excepciones.ResourceConflictException;
import co.edu.unicundi.discotiendaejbjar.excepciones.ResourceNotFoundException;
import co.edu.unicundi.discotiendaejbjar.servicio.IRolServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase que proporciona los servicios del rol.
 * @author César Rodríguez
 * @author Eison Morales
 * @author Juan Páez
 * @author Diego Cobos
 */
@Stateless
@Path("roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RolControlador {
    
    /**
     * Permite la conexión con el EJB para adquirir los servicios.
     */
    @EJB
    private IRolServicio servicio;
    
    /**
     * Método GET que permite buscar un rol por id.
     * @param id
     * @return Response
     */
    @GET
    @Path("/buscarPorId/{id}")
    public Response buscarPorId(@Valid @PathParam("id") Integer id)throws ResourceNotFoundException{
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarPorId(id))
                .build();
    }
    
    /**
     * Método GET que permite buscar a todos los roles.
     * @return Response
     */
    @GET
    @Path("/buscarTodos")
    public Response buscarTodos(){
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarTodo())
                .build();
    }
    
    /**
     * Método POST que permite registrar un rol.
     * @param rol
     * @return Response
     */
    @POST
    @Path("/registrar")
    public Response registrar(@Valid Rol rol)throws ResourceNotFoundException, EntityValidationException, ResourceConflictException{
        this.servicio.registrar(rol);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }
    
    /**
     * Método PUT que permite actualizar un rol.
     * @param rol
     * @return Response
     */
    @PUT
    @Path("/actualizar")
    public Response actualizar(@Valid Rol rol)throws BussinessException, ResourceNotFoundException, EntityValidationException, ResourceConflictException{
        this.servicio.actualizar(rol);
        return Response
                .status(Response.Status.OK)
                .build();
    }
    
    /**
     * Método DELETE que permite eliminar un rol por JPQL.
     * @param id
     * @return Response
     */
    @DELETE
    @Path("/eliminarPorIdJPQL/{id}")
    public Response eliminarPorIdJPQL(@Valid @PathParam("id") Integer id)throws ResourceNotFoundException{
        this.servicio.eliminarJPQL(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    
    /**
     * Método DELETE que permite eliminar un rol por SQL.
     * @param id
     * @return Response
     */
    @DELETE
    @Path("/eliminarPorIdSQL/{id}")
    public Response eliminarPorIdSQL(@Valid @PathParam("id") Integer id)throws ResourceNotFoundException{
        this.servicio.eliminarSQL(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    
}
