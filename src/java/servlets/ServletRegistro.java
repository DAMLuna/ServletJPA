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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.AndlunUserGame;

/**
 *
 * @author alfas
 */
public class ServletRegistro extends HttpServlet {
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
            AndlunUserGameJpaController userJpaContr = new AndlunUserGameJpaController(emf);
            List<AndlunUserGame> usergame = userJpaContr.findAndlunUserGameEntities();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServlRegistro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de users</h1>");
            if (usergame != null && !usergame.isEmpty()) {
                for (AndlunUserGame andlunUserGame : usergame) {
                    out.println("+ " + andlunUserGame.getIdUser() + " -> " + andlunUserGame.getNameUser() + " + " + andlunUserGame.getPasswd()+"<br>");
                }

            } else {
                out.println("<p style='color:red;'>No hay usuarios todavia</p>");
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
        try {
//            processRequest(request, response);
            AndlunUserGameJpaController userJpaContr = new AndlunUserGameJpaController(emf);//llama a la clase de controlador
            AndlunUserGame jpauser = new AndlunUserGame();//llama a la clase de jpa
            Encriptacion encript = new Encriptacion(); //llama a la clase encriptacion para los passwords
            String name = request.getParameter("nick");//guardo el contenido del formulario en variables
            String pass1 = encript.md5(request.getParameter("passwd1"));
            String pass2 = encript.md5(request.getParameter("passwd2"));
            String email = request.getParameter("email");
            if (pass1.contentEquals(pass2)) {
                //Introduzco los atributos de user a su clase para introducirlos a la base de datos.
                jpauser.setNameUser(name);
                jpauser.setPasswd(pass1);
                jpauser.setEmail(email);
                userJpaContr.create(jpauser); 
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet ServlRegistro</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Registro Completado!!</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } else {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet ServlRegistro</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Las contraseñas no coinciden</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServletRegistro.class.getName()).log(Level.SEVERE, null, ex);
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
