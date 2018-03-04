/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhannt.member;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nhannt.connection.MyConnection;

/**
 *
 * @author Trung Nhan
 */
public class MemberDAO implements Serializable {

    public String checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select RoleID From Member "
                        + "Where Username = ? and Password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("RoleID");
                    System.out.println("role = " + role);
                    return role;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }

        return null;
    }

    public boolean insertMembers(String username, String password, String firstname,
            String lastname, String email, String website, String roleID, boolean sendNoti) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Insert into Member (Username, Password, FirstName, "
                        + "LastName, Email, Website, RoleID, SendEmail) values (?,?,?,?,?,?,?,?)";
                System.out.println("Vào đc insert dao");
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, firstname);
                stm.setString(4, lastname);
                stm.setString(5, email);
                stm.setString(6, website);
                stm.setString(7, roleID);
                stm.setBoolean(8, sendNoti);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void loadUserInfo(String name) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select Username, Firstname, LastName, Email, Website, RoleMember.RoleName, Member.SendEmail "
                        + "From Member, RoleMember "
                        + "Where Member.RoleID = RoleMember.RoleID and Username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("Username");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String email = rs.getString("Email");
                    String website = rs.getString("Website");
                    String roleID = rs.getString("RoleName");
                    boolean sendNofi = rs.getBoolean("SendEmail");
                    MemberDTO dto = new MemberDTO(username, firstName, lastName, email, website, roleID, sendNofi);
                    if (this.listUser == null) {
                        this.listUser = new ArrayList<>();
                    }
                    this.listUser.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }

    }

    public void searchByLastFistName(String searchValue, String roleID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select Username, Firstname, LastName, Email, Website, RoleMember.RoleName "
                        + "From Member, RoleMember "
                        + "Where Member.RoleID = RoleMember.RoleID and Username LIKE ?"
                        + ((roleID == null || roleID == "")? "" : " and Member.RoleID = ?");
                stm = con.prepareStatement(sql);
                System.out.println(sql);
                stm.setString(1, "%" + searchValue + "%");
                if(roleID != null && roleID != ""){
                    stm.setString(2, roleID);
                }
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("Username");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String email = rs.getString("Email");
                    String website = rs.getString("Website");
                    String roleName = rs.getString("RoleName");
                    MemberDTO dto = new MemberDTO(username, firstName, lastName, email, website, roleName, true);
                    if (this.listUser == null) {
                        this.listUser = new ArrayList<>();
                    }
                    this.listUser.add(dto);

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public void loadAllUser() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select Username, FirstName, LastName, Email, Website, r.RoleName, Member.SendEmail "
                        + "From Member , RoleMember r "
                        + "Where Member.RoleID = r.RoleID ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("Username");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String email = rs.getString("Email");
                    String website = rs.getString("Website");
                    String roleID = rs.getString("RoleName");
                    boolean sendNoti = rs.getBoolean("SendEmail");
                    MemberDTO dto = new MemberDTO(username, firstName, lastName, email, website, roleID, sendNoti);
                    if (this.listUser == null) {
                        this.listUser = new ArrayList<>();
                    }
                    this.listUser.add(dto);

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }

    }

    public void loadMemberWithRole(String roleID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select Username, FirstName, LastName, Email,Website, RoleMember.RoleName, Member.SendEmail "
                        + "From Member, RoleMember"
                        + " Where Member.RoleID = RoleMember.RoleID  and Member.RoleID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, roleID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("Username");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String email = rs.getString("Email");
                    String website = rs.getString("Website");
                    String userRoleID = rs.getString("RoleName");
                    boolean sendNoti = rs.getBoolean("SendEmail");
                    MemberDTO dto = new MemberDTO(username, firstName, lastName, email, website, userRoleID,sendNoti);

                    if (this.listUser == null) {
                        this.listUser = new ArrayList<>();
                    }
                    this.listUser.add(dto);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }

    }


    public boolean changeRole(String roleID, String username) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                System.out.println("Role Id: " + roleID);
                String sql = "Update Member Set RoleID = ? Where Username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, roleID);
                stm.setString(2, username);
                int row = stm.executeUpdate();
                System.out.println("rs = " + row);
                while (row > 0) {
                    return true;
                }
            }

        } finally {

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }

        return false;
    }
    
    private List<MemberDTO> listUser;

    public List<MemberDTO> getListUser() {
        return listUser;
    }

    public int countAllMember() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select COUNT(*) AS SIZE From Member";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int size = rs.getInt("SIZE");
                    return size;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public int countMemberWithRole(String roleID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                System.out.println("DAO Role ID: " + roleID);
                String sql = "Select COUNT(*) AS SIZE From Member Where RoleID = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, roleID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int size = rs.getInt("SIZE");
                    return size;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return 0;
    }
    //Load RoleName
    //Check Exist User
    public boolean checkExistUser(String userID) throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select Username From Member Where Username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
            
        
        return false;
    }
}
