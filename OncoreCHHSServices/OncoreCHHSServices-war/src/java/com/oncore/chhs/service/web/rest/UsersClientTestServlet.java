/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.service.web.rest;

import com.oncore.chhs.client.dto.Summaries;
import com.oncore.chhs.client.dto.UserDTO;
import com.oncore.chhs.client.rest.UsersServiceClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kerry O'Brien
 */
@WebServlet( name = "UsersClientTestServlet", loadOnStartup = 1)
public class UsersClientTestServlet extends HttpServlet {

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

        UsersServiceClient client = new UsersServiceClient();
        
        //UserDTO user = client.getUser( 1 );
        
        Summaries result = client.searchUsers( "SMITH", "TOM" );
        
        try (PrintWriter out = response.getWriter()) { 
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestClientServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestClientServlet at " + request.getContextPath() + "</h1>");

            //out.println("<p>" + user.getFirstName() + " " + user.getLastName() );
            
            for (int i = 0;result != null && i < result.getItems().size();i++) {
                out.println( "<li>" + result.getItems().get(i).getFirstName() + " " + result.getItems().get( i ).getLastName() + "</li>");
            }

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
        processRequest(request, response);
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
