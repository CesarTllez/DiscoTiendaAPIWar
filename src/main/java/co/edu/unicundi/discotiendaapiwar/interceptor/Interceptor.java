/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.interceptor;

import co.edu.unicundi.discotiendaejbjar.servicio.ITokenServicio;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

/**
 * Clase que permite filtrar que rol puede o no consumir cada servicio.
 *
 * @author César Rodríguez
 * @author Eison Morales
 * @author Juan Páez
 * @author Diego Cobos
 */
@Provider
@PreMatching
public class Interceptor implements ContainerRequestFilter {

    /**
     * Permite la conexión con el EJB para adquirir los servicios.
     */
    @EJB
    private ITokenServicio servicio;

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

        //Validar token de acuerdo con el servicio que corresponda.
        if (token == null) {
            //---------------------Retornar exepcion wrapper.---------------------------------------
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("TOKEN NO VALIDO")
                    .build()
            //------------------------------------------------------------------------------------
            );
            return;
        } else {
           // if (this.servicio.validarExistenciaPorContenido(token) == 1) {
                try {

                    Claims claims = Jwts.parser()
                            .setSigningKey(DatatypeConverter.parseBase64Binary(llaveToken))
                            .parseClaimsJws(token).getBody();
                    

                    if (((ruta.contains("/artistas/"))
                            || (ruta.contains("/discos/"))
                            || (ruta.contains("/canciones/"))
                            || (ruta.contains("/usuarios/buscarTodos"))
                            || (ruta.contains("/usuarios/buscarPorId")))
                            && (claims.toString().contains("Administrador"))) {
                        return;
                    } else if (((ruta.contains("/artistas/buscarTodos"))
                            || (ruta.contains("/artistas/buscarPorId"))
                            || (ruta.contains("/discos/buscarTodos"))
                            || (ruta.contains("/discos/buscarPorId"))
                            || (ruta.contains("/discos/buscarPorNombre"))
                            || (ruta.contains("/canciones/buscarTodos"))
                            || (ruta.contains("/canciones/buscarTodosPorIdDisco"))
                            || (ruta.contains("/canciones/buscarPorId"))
                            || (ruta.contains("/canciones/buscarPorNombre")))
                            && (claims.toString().contains("Cliente"))) {
                        return;
                    } else {
                        //---------------------Retornar exepcion wrapper.---------------------------------------
                        requestContext.abortWith(Response
                                .status(Response.Status.UNAUTHORIZED)
                                .entity("TOKEN NO PERMITIDO PARA ESTA OPERACION")
                                .build()
                        //------------------------------------------------------------------------------------
                        );
                        return;
                    }

                } catch (ExpiredJwtException e) {
                    //---------------------Retornar exepcion wrapper.---------------------------------------
                    requestContext.abortWith(Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity("TOKEN CADUCADO")
                            .build()
                    //------------------------------------------------------------------------------------
                    );
                    return;
                }
           /* }else{
                //---------------------Retornar exepcion wrapper.---------------------------------------
                    requestContext.abortWith(Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity("TOKEN NO VALIDO")
                            .build()
                    //------------------------------------------------------------------------------------
                    );
                    return;
            }*/

        }

    }

}
