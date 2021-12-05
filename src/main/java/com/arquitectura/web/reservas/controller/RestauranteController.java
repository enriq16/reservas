/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitectura.web.reservas.controller;

import com.arquitectura.web.reservas.ejb.RestauranteDAO;
import com.arquitectura.web.reservas.entity.Restaurante;
import com.arquitectura.web.reservas.util.JsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USUARIO
 */
@WebServlet(name = "RestauranteController", urlPatterns = {"/RestauranteController"})
public class RestauranteController extends HttpServlet {

    @Inject
    RestauranteDAO restDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        List<Restaurante> list = restDAO.getRestaurantes();                                      
        request.setAttribute("list", list);
        
        String jspContent = "/WEB-INF/interface/restaurante.jsp";
        request.getRequestDispatcher(jspContent).forward(request, response); 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion != null) {
            switch (accion) {
                case "editar":
                    editarCliente(request, response);
                    break;
                case "agregar":
                    String jspContent = "/WEB-INF/interface/agregarEditarRestaurante.jsp";
                    request.getRequestDispatcher(jspContent).forward(request, response); 
                    break;
                default:
                    processRequest(request, response);
            }
        } else {
            processRequest(request, response);
        }
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        JsonResponse jresp = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();                
        String restauranteJsonString;
        
        try{
        //Integer id           = Integer.parseInt(req.getParameter("id"));                             
        String nombreRest    = req.getParameter("nombre");
        String direccionRest = req.getParameter("direccion");
        
        Restaurante r = new Restaurante();
        
        //r.setIdRestaurante(id);
        r.setNombre(nombreRest);
        r.setDireccion(direccionRest);
                                
        restDAO.crearRest(r);
        jresp.setStatus("exito");
        jresp.setMensaje("Restaurante con Id: "+r.getIdRestaurante()+" Creado.");
        jresp.setError("");
        
        
        }catch(Exception e){
            jresp.setStatus("noexito");
            jresp.setMensaje("");
            jresp.setError(e.getMessage());
        }        
        
        restauranteJsonString = objectMapper.writeValueAsString(jresp);        
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(restauranteJsonString);
        out.flush();        
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id    = Integer.parseInt(req.getParameter("id"));
        Restaurante r = new Restaurante();
        r.setIdRestaurante(id);
        restDAO.deleteRest(r);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "Exito");
        ObjectMapper objectMapper = new ObjectMapper();                
        String restauranteJsonString = objectMapper.writeValueAsString(respuesta);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(restauranteJsonString);
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonResponse jresp = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();                
        String restauranteJsonString;
        
        try{
        Integer id           = Integer.parseInt(req.getParameter("id"));                             
        String nombreRest    = req.getParameter("nombre");
        String direccionRest = req.getParameter("direccion");
        
        Restaurante r = new Restaurante();
        
        r.setIdRestaurante(id);
        r.setNombre(nombreRest);
        r.setDireccion(direccionRest);
                                
        restDAO.updateRest(r);
        jresp.setStatus("exito");
        jresp.setMensaje("Restaurante con Id: "+id+" Actualizado.");
        jresp.setError("");
        
        
        }catch(Exception e){
            jresp.setStatus("noexito");
            jresp.setMensaje("");
            jresp.setError(e.getMessage());
        }        
        
        restauranteJsonString = objectMapper.writeValueAsString(jresp);        
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(restauranteJsonString);
        out.flush();        
                
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Restaurante r = restDAO.findById(id);
        request.setAttribute("cc", r);
        String jspContent = "/WEB-INF/interface/agregarEditarRestaurante.jsp";
        request.getRequestDispatcher(jspContent).forward(request, response); 
    }

}
