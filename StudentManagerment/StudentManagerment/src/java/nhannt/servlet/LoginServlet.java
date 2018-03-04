/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhannt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhannt.member.MemberDAO;

public class LoginServlet extends HttpServlet {

    private final String errorPage = "Invalid.html";
    private final String showInfoServlet = "ShowInfoServlet";
    private final String adminServlet = "AdminServlet";
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
        PrintWriter out = response.getWriter();
        String url = errorPage;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            MemberDAO dao = new MemberDAO();
            System.out.println("dao = " + dao);
            String result = dao.checkLogin(username, password);
            System.out.println("result = " + result);
            if (result.equals("1")) {
                url = adminServlet;
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
            } else if (result.equals("2")) {
                url = showInfoServlet;
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
                System.out.println("url = " + url);
            }
        } catch (SQLException e) {
            log("Can not connect DB in LoginServlet", e);
        } catch (ClassNotFoundException e) {
            log("Can find Class in LoginServlet", e);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
