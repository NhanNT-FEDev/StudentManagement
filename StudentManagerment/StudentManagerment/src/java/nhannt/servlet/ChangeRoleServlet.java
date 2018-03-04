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

/**
 *
 * @author Trung Nhan
 */
public class ChangeRoleServlet extends HttpServlet {
    private final String adminServlet = "AdminServlet";
    private final String loginPage = "Login.jsp";
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
        String url = adminServlet;
        String[] checkList = request.getParameterValues("chkAdmin");
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("USERNAME");
        String changeRole = request.getParameter("ChangeRole");
        System.out.println("changeRole = " + changeRole);
        System.out.println("checkList = " + checkList);
        //Lấy list các username đc check r mới truy cập dao.
        try {
            MemberDAO dao = new MemberDAO();            
            for (int i = 0; i < checkList.length; i++) {
                System.out.println("Username: " + checkList[i]);
                dao.changeRole(changeRole, checkList[i]);
                if(username.equals(checkList[i]) && !changeRole.equals("1")){
                    url = loginPage;
                }
                System.out.println("i = " + i);
            }
        } catch (SQLException e) {
            log("Can not connect DB in Change Role", e);
        } catch (ClassNotFoundException e) {
            log("Can not find class in Change Role", e);
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
