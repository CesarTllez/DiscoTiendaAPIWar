/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.controlador;

import co.edu.unicundi.discotiendaejbjar.entidad.Cancion;
import co.edu.unicundi.discotiendaejbjar.excepciones.BussinessException;
import co.edu.unicundi.discotiendaejbjar.excepciones.EntityValidationException;
import co.edu.unicundi.discotiendaejbjar.excepciones.ResourceConflictException;
import co.edu.unicundi.discotiendaejbjar.excepciones.ResourceNotFoundException;
import co.edu.unicundi.discotiendaejbjar.excepciones.UnauthorizedException;


import co.edu.unicundi.discotiendaejbjar.servicio.ICancionServicio;
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
 * Clase que proporciona los servicios de las canciones.
 * @author César Rodríguez
 * @author Eison Morales
 * @author Juan Páez
 * @author Diego Cobos
 */
@Stateless
@Path("canciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CancionControlador {
    
    /**
     * Permite la conexión con el EJB para adquirir los servicios.
     */
    @EJB
    private ICancionServicio servicio;
    
    /**
     * Método GET que permite buscar una canción por id.
     * @param id
     * @return 
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
     * Método GET que permite buscar una canción por nombre.
     * @param nombre
     * @return 
     */
    @GET
    @Path("/buscarPorNombre/{nombre}")
    public Response buscarPorNombre(@Valid @PathParam("nombre") String nombre)throws ResourceNotFoundException{
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarPorNombre(nombre))
                .build();
    }
    
    /**
     * Método GET que permite buscar todas las canciones por id del disco.
     * @param idDisco
     * @return 
     */
    @GET
    @Path("/buscarTodosPorIdDisco/{idDisco}")
    public Response buscarTodosPorIdDisco(@Valid @PathParam("idDisco") Integer idDisco)throws ResourceNotFoundException {
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarTodosPorIdDisco(idDisco))
                .build();
    }
    
    /**
     * Método GET que permite buscar todas las canciones.
     * @return 
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
     * Método POST que permite registrar una canción.
     * @param cancion
     * @return 
     */
    @POST
    @Path("/registrar")
    public Response registrar(@Valid Cancion cancion)throws EntityValidationException, ResourceNotFoundException, ResourceConflictException, UnauthorizedException{
        this.servicio.registrar(cancion);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }
    
    /**
     * Método PUT que permite actualizar una canción.
     * @param cancion
     * @return 
     */
    @PUT
    @Path("/actualizar")
    public Response actualizar(@Valid Cancion cancion)throws BussinessException, ResourceNotFoundException, EntityValidationException,ResourceConflictException{
        this.servicio.actualizar(cancion);
        return Response
                .status(Response.Status.OK)
                .build();
    }
    
    /**
     * Método DELETE que permite eliminar una canción por JPQL.
     * @param id
     * @return 
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
     * Método DELETE que permite eliminar una canción por SQL.
     * @param id
     * @return 
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
