/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.controlador;

import co.edu.unicundi.discotiendaejbjar.entidad.Cliente;
import co.edu.unicundi.discotiendaejbjar.servicio.IClienteServicio;
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
 *
 * @author cesar
 */
@Stateless
@Path("clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {
    
    @EJB
    private IClienteServicio servicio;
    
    /**
     * Método GET que permite buscar a un cliente por id.
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
     * Método GET que permite buscar a un cliente por cedula.
     * @param cedula
     * @return Response
     */
    @GET
    @Path("/buscarPorCedula/{cedula}")
    public Response buscarPorCedula(@Valid @PathParam("cedula") String cedula){
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarPorCedula(cedula))
                .build();
    }
    
    /**
     * Método GET que permite buscar a un cliente por correo.
     * @param correo
     * @return Response
     */
    @GET
    @Path("/buscarPorCorreo/{correo}")
    public Response buscarPorCorreo(@Valid @PathParam("correo") String correo){
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarPorCorreo(correo))
                .build();
    }
    
    /**
     * Método GET que permite buscar a todos los clientes.
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
     * Método POST que permite registrar a un cliente.
     * @param cliente
     * @return Response
     */
    @POST
    @Path("/registrar")
    public Response registrar(@Valid Cliente cliente){
        this.servicio.registrar(cliente);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }
    
    /**
     * Método PUT que permite actualizar a un cliente.
     * @param cliente
     * @return Response
     */
    @PUT
    @Path("/actualizar")
    public Response actualizar(@Valid Cliente cliente){
        this.servicio.actualizar(cliente);
        return Response
                .status(Response.Status.OK)
                .build();
    }
    
    /**
     * Método DELETE que permite eliminar un cliente por JPQL.
     * @param id
     * @return Response
     */
    @DELETE
    @Path("/eliminarPorIdJPQL/{id}")
    public Response eliminarPorIdJPQL(@Valid @PathParam("id") Integer id){
        this.servicio.eliminarJPQL(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    
    /**
     * Método DELETE que permite eliminar un cliente por SQL.
     * @param id
     * @return Response
     */
    @DELETE
    @Path("/eliminarPorIdSQL/{id}")
    public Response eliminarPorIdSQL(@Valid @PathParam("id") Integer id){
        this.servicio.eliminarSQL(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    
}
