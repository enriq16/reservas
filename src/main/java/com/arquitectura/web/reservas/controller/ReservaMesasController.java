/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitectura.web.reservas.controller;

import com.arquitectura.web.reservas.ejb.ClienteDAO;
import com.arquitectura.web.reservas.ejb.MesasDAO;
import com.arquitectura.web.reservas.ejb.RestauranteDAO;
import com.arquitectura.web.reservas.entity.Cliente;
import com.arquitectura.web.reservas.entity.Mesas;
import com.arquitectura.web.reservas.entity.Restaurante;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ReservaMesasController", urlPatterns = {"/ReservaMesasController"})
public class ReservaMesasController extends HttpServlet {

    @Inject
    ClienteDAO clienteDAO;
    
    @Inject
    MesasDAO mesaDAO;
    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReservaMesasController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReservaMesasController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String fechaString        = request.getParameter("fecha");
        String rangoHora          = request.getParameter("rangoHora");
        String cantidadSolicitada = request.getParameter("cantidadSolicitada");
        
        Integer idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente c = clienteDAO.findById(idCliente);
        
        Integer idMesa = Integer.parseInt(request.getParameter("idMesa"));
        Mesas m = mesaDAO.getMesaById(idMesa);
        
        Integer idRestaurante = Integer.parseInt(request.getParameter("idRestaurante"));
        Restaurante rest = restDAO.findById(idRestaurante);
        
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
