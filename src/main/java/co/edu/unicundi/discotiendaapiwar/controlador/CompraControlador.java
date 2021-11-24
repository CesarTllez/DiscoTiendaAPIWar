/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.controlador;

import co.edu.unicundi.discotiendaejbjar.entidad.Compra;
import co.edu.unicundi.discotiendaejbjar.servicio.ICompraServicio;
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
 * Clase que proporciona los servicios de las compras.
 * @author César Rodríguez
 * @author Eison Morales
 * @author Juan Páez
 * @author Diego Cobos
 */
@Stateless
@Path("compras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompraControlador {
    
    @EJB
    private ICompraServicio servicio;
    
    /**
     * Método GET que permite buscar todas las compras.
     * @return 
     */
    @GET
    @Path("/buscarTodo")
    public Response buscarTodos() {
        return Response
                .status(Response.Status.OK)
                .entity(this.servicio.buscarTodo())
                .build();
    }
    
    /**
     * Método POST que permite registrar un disco.
     * @param compra
     * @return 
     */
    @POST
    @Path("/registrar")
    public Response registrar(Compra compra) {
        this.servicio.registrar(compra);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }
    
}
