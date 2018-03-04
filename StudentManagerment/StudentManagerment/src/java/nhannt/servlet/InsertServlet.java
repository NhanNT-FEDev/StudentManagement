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
import nhannt.member.MemberDAO;

/**
 *
 * @author Trung Nhan
 */
public class InsertServlet extends HttpServlet {

//    private final String adminServlet = "AdminServlet";
//    private final String errorPage = "ShowRoleServlet";
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
        String url = adminServlet;
        String username = request.getParameter("insertUserID");
        String password = request.getParameter("insertPassword");
        String firstName = request.getParameter("insertFirstName");
        String lastName = request.getParameter("insertLastName");
        String email = request.getParameter("insertEmail");
        String website = request.getParameter("insertWebsite");
        String roleID = request.getParameter("Role"); //Chưa sữa cho role trong trang jsp
//        boolean sendNoti = Boolean.parseBoolean(request.getParameter("chkSendNoti")); // chưa chỉnh hen trang jsp
        String chekedNoti = request.getParameter("chkSendNoti");
        System.out.println("chekedNoti = " + chekedNoti);
        try {
            //insert user to DB
            System.out.println("no vào d0c truy");
            MemberDAO dao = new MemberDAO();

            if (chekedNoti == null) {
                boolean result = dao.insertMembers(username, password, firstName, lastName, email, website, roleID, false);
                if (result) {
                    url = adminServlet;
                }
            } else {
                boolean result = dao.insertMembers(username, password, firstName, lastName, email, website, roleID, true);
                if (result) {
                    url = adminServlet;
                }
            }

            // Bấm insert new user => show role servlet => insert.jsp => check exist servlet => (true) => show role servlet 
                                                                                            // (false) => InsertServlet => AdminServlet
        } catch (SQLException e) {
            log("Wrong with insert into DB" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("Can not find class insert" + e.getMessage());
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
