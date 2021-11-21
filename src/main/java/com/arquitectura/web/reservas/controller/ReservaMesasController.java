
package com.arquitectura.web.reservas.controller;

import com.arquitectura.web.reservas.ejb.ReservaMesasDAO;
import com.arquitectura.web.reservas.entity.ReservaMesas;
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
 * @author Karen
 */

@WebServlet(name = "ReservaMesasController", urlPatterns = {"/ReservaMesasController"})
public class ReservaMesasController extends HttpServlet {

    @Inject
    ReservaMesasDAO rmesasDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
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

}
