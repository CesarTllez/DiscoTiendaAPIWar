/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.controlador;

import co.edu.unicundi.discotiendaejbjar.entidad.Disco;
import co.edu.unicundi.discotiendaejbjar.excepciones.BussinessException;
import co.edu.unicundi.discotiendaejbjar.excepciones.EntityValidationException;
import co.edu.unicundi.discotiendaejbjar.excepciones.ResourceConflictException;
import co.edu.unicundi.discotiendaejbjar.excepciones.ResourceNotFoundException;
import co.edu.unicundi.discotiendaejbjar.servicio.IDiscoServicio;
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
 * Clase que proporciona los servicios del disco.
 * @author César Rodríguez
 * @author Eison Morales
 * @author Juan Páez
 * @author Diego Cobos
 */
@Stateless
@Path("discos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DiscoController {

    /**
     * Permite la conexión con el EJB para adquirir los servicios.
     */
    @EJB
    private IDiscoServicio servicio;

    /**
     * Método GET que permite buscar un disco por id.
     * @param id
     * @return 
     */
    @GET
    @Path("/buscarPorId/{id}")
    public Response buscarPorId(@Valid @PathParam("id") Integer id)throws ResourceNotFoundException {
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarPorId(id))
                .build();
    }

    /**
     * Método GET que permite buscar un disco por nombre.
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
     * Método GET que permite buscar todos los discos.
     * @return 
     */
    @GET
    @Path("/buscarTodos")
    public Response buscarTodos() {
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarTodo())
                .build();
    }
    
    /**
     * Método GET que permite buscar todos los discos por id del artista.
     * @param idArtista
     * @return 
     */
    @GET
    @Path("/buscarTodosPorIdArtista/{idArtista}")
    public Response buscarTodosPorIdArtista(@PathParam("idArtista") Integer idArtista) {
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarTodosPorIdArtista(idArtista))
                .build();
    }
 
    /**
     * Método POST que permite registrar un disco.
     * @param disco
     * @return 
     */
    @POST
    @Path("/registrar")
    public Response registrar(@Valid Disco disco)throws ResourceNotFoundException, EntityValidationException, EntityValidationException, ResourceConflictException {
        this.servicio.registrar(disco);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    /**
     * Método PUT que permite actualizar un disco.
     * @param disco
     * @return 
     */
    @PUT
    @Path("/actualizar")
    public Response actualizar(@Valid Disco disco) throws BussinessException, ResourceNotFoundException, EntityValidationException, ResourceConflictException{
        this.servicio.actualizar(disco);
        return Response
                .status(Response.Status.OK)
                .build();
    }

    /**
     * Método DELETE que permite eliminar un disco por JPQL.
     * @param id
     * @return 
     */
    @DELETE
    @Path("/eliminarPorIdJPQL/{id}")
    public Response eliminarPorIdJPQL(@Valid @PathParam("id") Integer id)throws ResourceNotFoundException {
        this.servicio.eliminarJPQL(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    /**
     * Método DELETE que permite eliminar un disco por sQL.
     * @param id
     * @return 
     */
    @DELETE
    @Path("/eliminarPorIdSQL/{id}")
    public Response eliminarPorIdSQL(@Valid @PathParam("id") Integer id) throws ResourceNotFoundException {
        this.servicio.eliminarSQL(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

}
