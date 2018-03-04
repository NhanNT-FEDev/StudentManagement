/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhannt.rolemember;

import java.io.Serializable;

/**
 *
 * @author Trung Nhan
 */
public class RoleMemberDTO implements Serializable{
    private String roleID;
    private String roleName;

    public RoleMemberDTO() {
    }

    public RoleMemberDTO(String roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    
}
