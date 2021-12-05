

package com.arquitectura.web.reservas.controller;


import com.arquitectura.web.reservas.ejb.ClienteDAO;
import com.arquitectura.web.reservas.entity.Cliente;
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
 * @author Karen
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {

    @Inject
    ClienteDAO clienteDAO;

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Cliente> list = clienteDAO.listar();
        request.setAttribute("list", list);
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String jspContent = "/WEB-INF/interface/cliente.jsp";
        request.getRequestDispatcher(jspContent).forward(request, response); 
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Cliente c = new Cliente();
        c.setId(id);
        
        clienteDAO.deleteCliente(c);
        
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
        Cliente c = new Cliente();
        
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer ci = Integer.parseInt(req.getParameter("cedula"));
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        
        c.setId(id);
        c.setCedula(ci);
        c.setNombre(nombre);
        c.setApellido(apellido);
        
        clienteDAO.update(c);
        
        jresp.setStatus("exito");
        jresp.setMensaje("Cliente con Id: "+c.getId()+" Actualizado.");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonResponse jresp = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();                
        String restauranteJsonString;
        try{
        
        Cliente c = new Cliente();
        Integer ci = Integer.parseInt(req.getParameter("cedula"));
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
                
        c.setCedula(ci);
        c.setNombre(nombre);
        c.setApellido(apellido);
        
        clienteDAO.agregar(c);
        
        jresp.setStatus("exito");
        jresp.setMensaje("Cliente con Id: "+c.getId()+" Creado.");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        
        String accion = request.getParameter("accion");
        
        if (accion != null) {
            switch (accion) {
                case "editar":
                    editarCliente(request, response);
                    break;
                case "agregar":
                    String jspContent = "/WEB-INF/interface/agregarEditarCliente.jsp";
                    request.getRequestDispatcher(jspContent).forward(request, response); 
                    break;
                default:
                    processRequest(request, response);
            }
        } else {
            processRequest(request, response);
        }
        
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Cliente c = clienteDAO.findById(id);
        request.setAttribute("cc", c);
        String jspContent = "/WEB-INF/interface/agregarEditarCliente.jsp";
        request.getRequestDispatcher(jspContent).forward(request, response); 
    }
    
    
 
}
