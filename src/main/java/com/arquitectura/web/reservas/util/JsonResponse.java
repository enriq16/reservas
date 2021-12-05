/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitectura.web.reservas.util;

/**
 *
 * @author USUARIO
 */
public class JsonResponse {
    private String status;
    private String mensaje;
    private String error;

    public JsonResponse() {
    }

    public JsonResponse(String status, String mensaje, String error) {
        this.status = status;
        this.mensaje = mensaje;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getError() {
        return error;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
