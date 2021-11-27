/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.interceptor;

import co.edu.unicundi.discotiendaapiwar.excepciones.ExceptionWrapper;
import co.edu.unicundi.discotiendaejbjar.dto.TokenInterceptor;
import co.edu.unicundi.discotiendaejbjar.excepciones.ResourceNotFoundException;
import co.edu.unicundi.discotiendaejbjar.servicio.ITokenServicio;
import co.edu.unicundi.discotiendaejbjar.servicio.IUsuarioServicio;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

/**
 * Clase que permite filtrar que rol puede o no consumir cada servicio.
 * @author César Rodríguez
 * @author Eison Morales
 * @author Juan Páez
 * @author Diego Cobos
 */
@Provider
@PreMatching
public class Interceptor implements ContainerRequestFilter {

    @Context
    private UriInfo urlExcepcion;
    ExceptionWrapper objeto = new ExceptionWrapper();
    /**
     * Permite la conexión con el EJB para adquirir los servicios.
     */
    @EJB
    private ITokenServicio servicio;

    /**
     * Permite la conexión con el EJB para adquirir los servicios.
     */
    @EJB
    private IUsuarioServicio servicioUsuario;

    /**
     * Método que filtra las peticiones.
     * @param requestContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        //Almacena la ruta del servicio que sea consumido.
        String ruta = requestContext.getUriInfo().getAbsolutePath().toString();

        //Almacena el token.
        String token = requestContext.getHeaderString("Authorization");

        //Almacena la llave del token;
        String llaveToken = "pW8xqK91$nBlOCEPD420876@tczH";

        //Rutas que no necesitan token.
        if ((ruta.contains("/sesiones/iniciar"))
                || (ruta.contains("usuarios/registrar"))) {
            return;
        }

        //Validar si se envió token o no en la petición..
        if (token == null) {
            objeto = new ExceptionWrapper("401", "Unauthorized", "No se envio un token.", urlExcepcion.getPath());
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(objeto)
                    .build()
            );
            return;
        } else {
            //Validar si existe token en la base de datos.
            if (this.servicio.validarExistenciaPorContenido(token) == 1) {
                try {

                    Claims claims = Jwts.parser()
                            .setSigningKey(DatatypeConverter.parseBase64Binary(llaveToken))
                            .parseClaimsJws(token).getBody();

                    //Volviendo claims a tipo gson para transferir la información a dto.
                    Gson gson = new Gson();
                    TokenInterceptor tokenInterceptor = gson.fromJson(gson.toJson(claims),
                            TokenInterceptor.class);

                    //Filtrar servicios de acuerdo con el rol que les correspondan.
                    if (((ruta.contains("/formatos/"))
                            || (ruta.contains("/artistas/"))
                            || (ruta.contains("/discos/"))
                            || (ruta.contains("/canciones/"))
                            || (ruta.contains("/usuarios/buscarTodos"))
                            || (ruta.contains("/usuarios/buscarPorId"))
                            || (ruta.contains("/usuarios/buscarPorApodo")))
                            && (tokenInterceptor.getRol().getNombre().equalsIgnoreCase("Administrador"))) {
                        return;
                    } else try {
                        if (((ruta.contains("/usuarios/buscarPorId/" + this.servicioUsuario.buscarPorApodo(
                                tokenInterceptor.getSub()).getId()))
                                || (ruta.contains("/usuarios/buscarPorApodo/" + tokenInterceptor.getSub()))
                                || (ruta.contains("/usuarios/buscarHistorialCompras"))
                                || (ruta.contains("/usuarios/actualizar"))
                                || (ruta.contains("/usuarios/eliminarPorIdJPQL/" + this.servicioUsuario.buscarPorApodo(
                                        tokenInterceptor.getSub()).getId()))
                                || (ruta.contains("/artistas/buscarTodos"))
                                || (ruta.contains("/artistas/vistaBuscar"))
                                || (ruta.contains("/artistas/buscarPorId"))
                                || (ruta.contains("/discos/buscarTodos"))
                                || (ruta.contains("/discos/buscarPorId"))
                                || (ruta.contains("/discos/historialVentaDisco"))
                                || (ruta.contains("/discos/buscarPorNombre"))
                                || (ruta.contains("/discos/comprar"))
                                || (ruta.contains("/canciones/buscarTodos"))
                                || (ruta.contains("/canciones/historialVentaCancion"))
                                || (ruta.contains("/canciones/buscarTodosPorIdDisco"))
                                || (ruta.contains("/canciones/buscarPorId"))
                                || (ruta.contains("/canciones/buscarPorNombre"))
                                || (ruta.contains("/canciones/comprar"))
                                || (ruta.contains("/formatos/buscarPorId"))
                                || (ruta.contains("/formatos/buscarTodos")))
                                && (tokenInterceptor.getRol().getNombre().equalsIgnoreCase("Cliente"))) {
                            return;
                        } else if ((ruta.contains("/sesiones/finalizar"))
                                && ((tokenInterceptor.getRol().getNombre().equals("Administrador"))
                                || (tokenInterceptor.getRol().getNombre().equals("Cliente")))) {
                            return;
                        } else {
                            objeto = new ExceptionWrapper("401", "Unauthorized", "Token no permitido para esta operacion.", urlExcepcion.getPath());
                            requestContext.abortWith(Response
                                    .status(Response.Status.UNAUTHORIZED)
                                    .entity(objeto)
                                    .build()
                            );
                            return;
                        }
                    } catch (ResourceNotFoundException e) {
                        objeto = new ExceptionWrapper("404", "Not_Found", "Ese apodo no esta registrado en la base de datos", urlExcepcion.getPath());
                            requestContext.abortWith(Response
                                    .status(Response.Status.NOT_FOUND)
                                    .entity(objeto)
                                    .build()
                            );
                        return;
                    }

                } catch (ExpiredJwtException e) {
                    objeto = new ExceptionWrapper("401", "Unauthorized", "Token caducado", urlExcepcion.getPath());
                    requestContext.abortWith(Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity(objeto)
                            .build()
                    );
                    return;
                }
            } else {
                objeto = new ExceptionWrapper("401", "Unauthorized", "Token no valido.", urlExcepcion.getPath());
                requestContext.abortWith(Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity(objeto)
                        .build()
                );
                return;

            }

        }

    }

}
