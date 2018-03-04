/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhannt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private final String loginPage = "Login.jsp";
    private final String loginServlet = "LoginServlet";
//    private final String insertServlet = "InsertServlet";
    private final String searchServlet = "SearchServlet";
    private final String changeRoleServlet = "ChangeRoleServlet";
    private final String loadAdminPage = "AdminServlet";
    private final String logOutServlet = "LogOutServlet";
    private final String showRoleServlet = "ShowRoleServlet";
    private final String checkExistUserServlet = "CheckExistUserServlet";
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
        String url = loginPage;
        try {
            String button = request.getParameter("btnAction");
            if (button.equals("Login")) {
                url = loginServlet;
            } else if (button.equals("Insert")) {
                System.out.println("Nó vào đc trang insertServlet");
                url = checkExistUserServlet;
            } else if (button.equals("Search")) {
                url = searchServlet;
            } else if (button.equals("Change")) {
                url = changeRoleServlet;
            } else if (button.equals("AdminPage")) {
                url = loadAdminPage;
            } else if (button.equals("LogOut")) {
                url = logOutServlet;
            } else if (button.equals("Insert New User")) {
                url = showRoleServlet;
            }
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
