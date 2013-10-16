/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_frontend_servlet;

import com.adde.webbapp_model.Person;
import com.adde.webbapp_model.ProjectPlatform;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eric
 */
@WebServlet(name = "FrontController", urlPatterns = {"/fc"})
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String view = request.getParameter("view");
        String action = request.getParameter("action");
        
//        Shop shop = (Shop) request.getServletContext().getAttribute(Keys.SHOP.toString());
//                IProductCatalogue pcat = shop.getProductCatalogue();
        
        ProjectPlatformWrapper projectPlatformWrapper = (ProjectPlatformWrapper) request.getServletContext().getAttribute("PROJECT_PLATFORM");
        ProjectPlatform projectPlatform = projectPlatformWrapper.getProjectPlatform();
        
        if (action != null) {
            switch (action) {
                case "validate":
                    String username = request.getParameter("username");
                    projectPlatform.addUser(new Person(username, username + "@" + username + ".com"));
                    
                    request.setAttribute("username", username);
                    request.getRequestDispatcher("WEB-INF/jsp/welcome.jspx").forward(request, response);
                    break;
                default:
                    ;
            }
        }
         // Navigation
        if (view != null) {
            switch (view) {
                case "home":
                    request.getRequestDispatcher("WEB-INF/index.jspx").forward(request, response);
                    break;
                case "signup":
                    request.getRequestDispatcher("WEB-INF/jsp/signup.jspx").forward(request, response);
                    break;
                case "about":
                    request.getRequestDispatcher("WEB-INF/jsp/about.jspx").forward(request, response);
                    break;
                case "contact":
                    request.getRequestDispatcher("WEB-INF/jsp/contact.jspx").forward(request, response);
                    break;
                case "welcome":
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    
                    request.setAttribute("username", username);
                    request.setAttribute("password", password);
                    projectPlatform.addUser(new Person(username, username + "@" + username + ".com"));
                    
                    request.getRequestDispatcher("WEB-INF/jsp/welcome.jspx").forward(request, response);
                    break;
                default:
                    ;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
