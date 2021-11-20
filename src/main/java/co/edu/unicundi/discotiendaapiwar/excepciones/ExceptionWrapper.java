/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discotiendaapiwar.excepciones;

import java.util.Date;

/**
 * Clase que proporciona los servicios del artista.
 * @author César Rodríguez
 * @author Eison Morales
 * @author Juan Páez
 * @author Diego Cobos
 */
public class ExceptionWrapper {
    
    /**
     * Almacena el código generado.
     */
    private String codigo;

    /**
     * Almacena el error generado.
     */
    private String error;

    /**
     * Almacena la fecha en la que se poducjo la excepción.
     */
    private String fecha;

    /**
     * Almacena el mensaje.
     */
    private String mensaje;

    /**
     * Almacena la url que produjo la excepción.
     */
    private String ulr;

    public ExceptionWrapper() {
    }

    public ExceptionWrapper(String codigo, String error, String mensaje, String ulr) {
        this.codigo = codigo;
        this.error = error;
        this.fecha = new Date().toString();
        this.mensaje = mensaje;
        this.ulr = ulr;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUlr() {
        return ulr;
    }

    public void setUlr(String ulr) {
        this.ulr = ulr;
    }

}
