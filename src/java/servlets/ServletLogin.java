/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import clases.Encriptacion;
import controllers.AndlunUserGameJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.AndlunUserGame;

/**
 *
 * @author alfas
 */
public class ServletLogin extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("3.4.3ServletJPAPU");

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
            out.println("<title>Servlet ServletLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletLogin at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        AndlunUserGameJpaController userJpaContr = new AndlunUserGameJpaController(emf);//llama a la clase de controlador
        List<AndlunUserGame> usergame = userJpaContr.findAndlunUserGameEntities();
        AndlunUserGame jpauser = new AndlunUserGame();
        String comprCookie;
        boolean nickCompr = false, passwCompr = false;
        Cookie ck[] = request.getCookies();
        for (Cookie cookie : ck) {
            comprCookie = cookie.getValue();
            for (AndlunUserGame andlunUserGame : usergame) {
                if (comprCookie.contentEquals(andlunUserGame.getNameUser())) {
                    nickCompr = true;
                }
                if (comprCookie.contentEquals(andlunUserGame.getPasswd())) {
                    passwCompr = true;
                }
            }
        }
        if (nickCompr == true && passwCompr == true) {
            response.sendRedirect("panelprincipal.jsp");
        } else {
            RequestDispatcher a = request.getRequestDispatcher("index2.jsp");
            a.forward(request, response);
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
//        processRequest(request, response);
        try {
            AndlunUserGameJpaController userJpaContr = new AndlunUserGameJpaController(emf);//llama a la clase de controlador
            List<AndlunUserGame> usergame = userJpaContr.findAndlunUserGameEntities();
            AndlunUserGame jpauser = new AndlunUserGame();//llama a la clase de jpa
            Encriptacion encript = new Encriptacion(); //llama a la clase encriptacion para los passwords
            String nick = request.getParameter("nick").toLowerCase();//guardo el contenido del formulario en variables
            String pass = encript.md5(request.getParameter("passwd"));
            boolean nickCompr = false;
            boolean passwCompr = false;
            if (usergame != null && !usergame.isEmpty()) {
                for (AndlunUserGame andlunUserGame : usergame) {
                    if (nick.contentEquals(andlunUserGame.getNameUser())) {
                        nickCompr = true;
                    }
                    if (pass.contentEquals(andlunUserGame.getPasswd())) {
                        passwCompr = true;
                    }
                }
                if (nickCompr == true && passwCompr == true) {
                    Cookie ckn = new Cookie("nick", nick);
                    Cookie ckp = new Cookie("passwd", pass);
                    ckn.setMaxAge(60*30);
                    ckp.setMaxAge(60*30);
                    ckn.setPath("/3.4.3ServletJPA");
                    ckp.setPath("/3.4.3ServletJPA");
                    response.addCookie(ckn);
                    response.addCookie(ckp);
                    response.sendRedirect("panelprincipal.jsp");
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        request.getRequestDispatcher("index2.jsp").include(request, response);
                       out.println("<p style='color:white;text-align: center'>El nombre de usuario o contraseña no son correctos<p>");
                      
                      
                    }
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
