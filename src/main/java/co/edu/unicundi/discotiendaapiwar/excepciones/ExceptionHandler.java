/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.excepciones;

import co.edu.unicundi.discotiendaejbjar.excepciones.EntityValidationException;
import co.edu.unicundi.discotiendaejbjar.excepciones.ResourceConflictException;
import co.edu.unicundi.discotiendaejbjar.excepciones.ResourceNotFoundException;
import co.edu.unicundi.discotiendaejbjar.excepciones.UnauthorizedException;
import javax.ejb.EJBException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Clase que proporciona los servicios del artista.
 * @author César Rodríguez
 * @author Eison Morales
 * @author Juan Páez
 * @author Diego Cobos
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception>{
       /**
     * Alamacena la url de la petición que produce la excepción.
     */
    @Context
    private UriInfo urlExcepcion;

    @Override
    public Response toResponse(Exception ex) {
        ExceptionWrapper wrapper;
        ex.printStackTrace();
        


        if (ex instanceof WebApplicationException) {
            Response status = ((WebApplicationException) ex).getResponse();
            System.out.println(status);

            switch (status.getStatus()) {
                case 400:
                    wrapper = new ExceptionWrapper(String.valueOf(status.getStatus()), String.valueOf(status.getStatusInfo()), ex.getMessage(),
                            urlExcepcion.getPath());
                    return Response.status(Response.Status.BAD_REQUEST).entity(wrapper).build();
                case 401: 
                    wrapper = new ExceptionWrapper(String.valueOf(status.getStatus()), String.valueOf(status.getStatusInfo()), ex.getMessage(),
                            urlExcepcion.getPath());
                    return Response.status(Response.Status.UNAUTHORIZED).entity(wrapper).build();
                case 404:
                    wrapper = new ExceptionWrapper(String.valueOf(status.getStatus()), String.valueOf(status.getStatusInfo()), ex.getMessage(),
                            urlExcepcion.getPath());
                    return Response.status(Response.Status.NOT_FOUND).entity(wrapper).build();

                case 405:
                    wrapper = new ExceptionWrapper(String.valueOf(status.getStatus()), String.valueOf(status.getStatusInfo()), ex.getMessage(),
                            urlExcepcion.getPath());
                    return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(wrapper).build();

                case 409:
                    wrapper = new ExceptionWrapper(String.valueOf(status.getStatus()), String.valueOf(status.getStatusInfo()), ex.getMessage(),
                            urlExcepcion.getPath());
                    return Response.status(Response.Status.CONFLICT).entity(wrapper).build();

                case 415:
                    wrapper = new ExceptionWrapper(String.valueOf(status.getStatus()), String.valueOf(status.getStatusInfo()), ex.getMessage(),
                            urlExcepcion.getPath());
                    return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(wrapper).build();

                default:
                    wrapper = new ExceptionWrapper(String.valueOf(status.getStatus()), String.valueOf(status.getStatusInfo()), ex.getMessage(),
                            urlExcepcion.getPath());
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(wrapper).build();
            }
        } else {

            if (ex instanceof EJBException) {
                wrapper = new ExceptionWrapper(
                        "500", "INTERNAL_SERVER_ERROR",
                        ex.getMessage(), urlExcepcion.getPath());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(wrapper).build();
            } else {
                if (ex instanceof EntityValidationException) {
                    wrapper = new ExceptionWrapper("400", "BAD_REQUEST", ex.getMessage(), urlExcepcion.getPath());
                    return Response.status(Response.Status.BAD_REQUEST).entity(wrapper).build();
                } else {
                    if (ex instanceof ResourceNotFoundException) {
                        wrapper = new ExceptionWrapper("404", "NOT_FOUND", ex.getMessage(), urlExcepcion.getPath());
                        return Response.status(Response.Status.NOT_FOUND).entity(wrapper).build();
                    } else {
                        if (ex instanceof ResourceConflictException) {
                            wrapper = new ExceptionWrapper("409", "CONFLICT", ex.getMessage(), urlExcepcion.getPath());
                            return Response.status(Response.Status.CONFLICT).entity(wrapper).build();
                        }else {
                        if (ex instanceof UnauthorizedException) {
                            wrapper = new ExceptionWrapper("401", "UNAUTHORIZED", ex.getMessage(), urlExcepcion.getPath());
                            return Response.status(Response.Status.UNAUTHORIZED).entity(wrapper).build();
                        }
                        }
                    }
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("").build();
                }
            }
        }
    }
}