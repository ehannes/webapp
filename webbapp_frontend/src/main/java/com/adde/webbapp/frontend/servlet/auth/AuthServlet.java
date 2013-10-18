package com.adde.webbapp.frontend.servlet.auth;

import com.adde.webbapp.frontend.servlet.DAOFactoryWrapper;
import com.adde.webbapp.model.dao.PersonCatalogue;
import com.adde.webbapp.model.entity.Person;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ehannes
 */
@WebServlet(urlPatterns = {"/auth"})
public class AuthServlet extends HttpServlet {

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
        DAOFactoryWrapper daoFactoryWrapper = (DAOFactoryWrapper) request.getServletContext().getAttribute("DAOFACTORY");
        PersonCatalogue personCatalogue = daoFactoryWrapper.getPersonDAO();
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (password != null && username != null) {
                    Logger.getAnonymousLogger().log(Level.INFO, "Name and password not null");
                    Person person = personCatalogue.getByUserName(username);
                    Logger.getAnonymousLogger().log(Level.INFO, "Person: " + person);
                    if (person != null
                            && person.getPassword().equals(password)) {
                        Logger.getAnonymousLogger().log(Level.INFO, "User found and correct password!");
                        HttpSession session = request.getSession(true);
                        session.setAttribute("person", person);
                        response.sendRedirect("fc?view=logedin"); //jsp/rs/welcome.jspx
                    } else{
                        Logger.getAnonymousLogger().log(Level.INFO, "User not found or wrong password!");
                        request.getRequestDispatcher("WEB-INF/jsp/login.jspx").forward(request, response);
                    }
                } else{
                    Logger.getAnonymousLogger().log(Level.INFO, "Incorrect input!");
                    request.getRequestDispatcher("WEB-INF/jsp/login.jspx").forward(request, response);
                }
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
