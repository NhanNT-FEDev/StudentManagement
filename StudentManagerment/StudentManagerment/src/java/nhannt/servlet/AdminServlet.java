/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhannt.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhannt.member.MemberDTO;
import nhannt.member.MemberDAO;
import nhannt.rolemember.RoleMemberDAO;
import nhannt.rolemember.RoleMemberDTO;

public class AdminServlet extends HttpServlet {

    private final String adminPage = "Admin.jsp";
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
        String url = adminPage;
        String action = request.getParameter("btnAction");
        System.out.println("BTN: " + action);
        String roleID = request.getParameter("roleID");
        try {
            MemberDAO memberdao = new MemberDAO();
            RoleMemberDAO roleDao = new RoleMemberDAO();
            roleDao.loadAllRole();
            List<RoleMemberDTO> listRole = roleDao.getListRole();
            request.setAttribute("LISTROLE", listRole);
            int allSize = memberdao.countAllMember();
                request.setAttribute("ALLSIZE", allSize);
            LinkedHashMap<RoleMemberDTO, Integer> listCount = new LinkedHashMap<>();
            for (int i = 0; i < listRole.size(); i++) {
                int countMember = memberdao.countMemberWithRole(listRole.get(i).getRoleID());
                listCount.put(listRole.get(i), countMember);                
            }
            request.setAttribute("LISTCOUNT", listCount);
            if (roleID == "" || roleID == null) {
                memberdao.loadAllUser();
                List<MemberDTO> listAllMember = memberdao.getListUser();
                request.setAttribute("LISTMEMBER", listAllMember);
                
            }else{
                memberdao.loadMemberWithRole(roleID);
                List<MemberDTO> listMember = memberdao.getListUser();
                request.setAttribute("ROLEID", roleID);
                request.setAttribute("LISTMEMBER", listMember);
            }
            
            //Load Role Here
           
            
            
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (SQLException e) {
            System.out.println("EX");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("EX");
            e.printStackTrace();
        } finally {

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
