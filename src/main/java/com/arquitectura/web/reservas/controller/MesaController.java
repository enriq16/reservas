/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitectura.web.reservas.controller;

import com.arquitectura.web.reservas.ejb.MesasDAO;
import com.arquitectura.web.reservas.ejb.RestauranteDAO;
import com.arquitectura.web.reservas.entity.Mesa;
import com.arquitectura.web.reservas.entity.Restaurante;
import com.arquitectura.web.reservas.util.JsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "MesaController", urlPatterns = {"/MesaController"})
public class MesaController extends HttpServlet {

    @Inject
    MesasDAO mesaDAO;

    @Inject
    RestauranteDAO restDao;

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

        List<Restaurante> listaRest = restDao.getRestaurantes();
        request.setAttribute("listRest", listaRest);

        String jspContent = "/WEB-INF/interface/Mesa.jsp";
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
        Integer idRest = 0;
        String idRestString = request.getParameter("idRest");
        
        if (accion == null) {
            
            if (idRestString != null) {

                idRest = Integer.parseInt(idRestString);
                request.setAttribute("idRest", idRest);
                List<Mesa> list = mesaDAO.getMesas(idRest);
                request.setAttribute("list", list);
                processRequest(request, response);

            } else {
                request.setAttribute("list", new ArrayList<Mesa>());
                processRequest(request, response);
            }            
        } else {
            idRest = Integer.parseInt(idRestString);
            request.setAttribute("idRest", idRest);
            List<Restaurante> listaRest = restDao.getRestaurantes();
            request.setAttribute("listRest", listaRest);
            
            switch (accion) {
                case "editar":                    
                    editarCliente(request, response);
                    break;
                case "agregar":
                    String jspContent = "/WEB-INF/interface/agregarEditarMesa.jsp";
                    request.getRequestDispatcher(jspContent).forward(request, response);
                    break;
                default:
                    request.setAttribute("list", new ArrayList<Mesa>());
                    processRequest(request, response);
            }
        }

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
        JsonResponse jresp = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();                
        String restauranteJsonString;
        try{
        Mesa m = new Mesa();

        String nombreMesa = request.getParameter("nombre_mesa");
        Integer idRest = Integer.parseInt(request.getParameter("id_restaurante"));
        Restaurante restaurante = restDao.findById(idRest);
        BigDecimal posicionLat = BigDecimal.valueOf(Double.parseDouble(request.getParameter("posicion_lat")));
        BigDecimal posicionLon = BigDecimal.valueOf(Double.parseDouble(request.getParameter("posicion_lon")));
        Integer planta = Integer.parseInt(request.getParameter("planta"));
        Integer capacidad = Integer.parseInt(request.getParameter("capacidad"));

        m.setNombreMesa(nombreMesa);
        m.setRestaurante(restaurante);
        m.setPosicionLat(posicionLat);
        m.setPosicionLon(posicionLon);
        m.setPlanta(planta);
        m.setCapacidad(capacidad);

        mesaDAO.crearRest(m);
        jresp.setStatus("exito");
        jresp.setMensaje("Mesa con Id: "+m.getId()+" Creado.");
        jresp.setError("");
        
        }catch(Exception e){
            jresp.setStatus("noexito");
            jresp.setMensaje("");
            jresp.setError(e.getMessage());
        }
        restauranteJsonString = objectMapper.writeValueAsString(jresp);
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(restauranteJsonString);
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonResponse jresp = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();                
        String restauranteJsonString;
        try{
        Mesa m = new Mesa();

        Integer id = Integer.parseInt(request.getParameter("id"));
        String nombreMesa = request.getParameter("nombre_mesa");
        Integer idRest = Integer.parseInt(request.getParameter("id_restaurante"));
        Restaurante restaurante = restDao.findById(idRest);
        BigDecimal posicionLat = BigDecimal.valueOf(Double.parseDouble(request.getParameter("posicion_lat")));
        BigDecimal posicionLon = BigDecimal.valueOf(Double.parseDouble(request.getParameter("posicion_lon")));
        Integer planta = Integer.parseInt(request.getParameter("planta"));
        Integer capacidad = Integer.parseInt(request.getParameter("capacidad"));

        m.setId(id);
        m.setNombreMesa(nombreMesa);
        m.setRestaurante(restaurante);
        m.setPosicionLat(posicionLat);
        m.setPosicionLon(posicionLon);
        m.setPlanta(planta);
        m.setCapacidad(capacidad);

        mesaDAO.updateRest(m);
        jresp.setStatus("exito");
        jresp.setMensaje("Mesa con Id: "+m.getId()+" Actualizado.");
        jresp.setError("");
        
        }catch(Exception e){
            jresp.setStatus("noexito");
            jresp.setMensaje("");
            jresp.setError("Error: "+e.getMessage());
        }
        restauranteJsonString = objectMapper.writeValueAsString(jresp);
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
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
        Mesa cc = mesaDAO.getMesaById(id);
        request.setAttribute("cc", cc);
        String jspContent = "/WEB-INF/interface/agregarEditarMesa.jsp";
        request.getRequestDispatcher(jspContent).forward(request, response);
    }

}
