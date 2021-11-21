/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitectura.web.reservas.controller;

import com.arquitectura.web.reservas.ejb.RestauranteDAO;
import com.arquitectura.web.reservas.entity.Restaurante;
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
        List<Restaurante> list = (List<Restaurante>)request.getAttribute("list");
                
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();               
        
        out.print(list);
        out.flush();     
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
        String restauranteJsonString;
        List<Restaurante> list = restDAO.getRestaurantes();
                                      
        request.setAttribute("list", list);
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreRest    = request.getParameter("nombre_restaurante");
        String direccionRest = request.getParameter("direccion");
        
        Restaurante r = new Restaurante();
        r.setNombre(nombreRest);
        r.setDireccion(direccionRest);
        
        restDAO.crearRest(r);
        
        ObjectMapper objectMapper = new ObjectMapper();
                
        String restauranteJsonString = objectMapper.writeValueAsString(r);
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
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
        Integer id           = Integer.parseInt(req.getParameter("id"));
        String nombreRest    = req.getParameter("nombre_restaurante");
        String direccionRest = req.getParameter("direccion");
        
        Restaurante r = new Restaurante();
        r.setIdRestaurante(id);
        r.setNombre(nombreRest);
        r.setDireccion(direccionRest);
        
        restDAO.updateRest(r);
        
        ObjectMapper objectMapper = new ObjectMapper();                
        String restauranteJsonString = objectMapper.writeValueAsString(r);
        
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

}
