/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhannt.rolemember;

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
public class RoleMemberDAO implements Serializable{
    public void loadAllRole() throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select RoleID, RoleName From RoleMember";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    String roleID = rs.getString("RoleID");
                    String roleName = rs.getString("RoleName");
                    RoleMemberDTO dto = new RoleMemberDTO(roleID, roleName);
                    if (this.listRoleUser == null) {
                        this.listRoleUser = new ArrayList<>();
                    }
                    this.listRoleUser.add(dto);
                }
            }
        }finally{
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
    private List<RoleMemberDTO> listRoleUser;
    public List<RoleMemberDTO> getListRole(){
        return listRoleUser;
    }
}
