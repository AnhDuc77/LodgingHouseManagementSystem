/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class RoleOfStaff {
    private int roleStaffID;
    private String roleStaffName;

    public int getRoleStaffID() {
        return roleStaffID;
    }

    public void setRoleStaffID(int roleStaffID) {
        this.roleStaffID = roleStaffID;
    }

    public String getRoleStaffName() {
        return roleStaffName;
    }

    public void setRoleStaffName(String roleStaffName) {
        this.roleStaffName = roleStaffName;
    }

    @Override
    public String toString() {
        return "RoleOfStaff{" + "roleStaffID=" + roleStaffID +
                ", roleStaffName=" + roleStaffName + '}';
    }
    
    
}
