package com.rofe.pojo;

public class Role {
    private Integer roleId;

    private String roleName;

    private String roleExtra1;

    private String roleAuthority;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleExtra1() {
        return roleExtra1;
    }

    public void setRoleExtra1(String roleExtra1) {
        this.roleExtra1 = roleExtra1 == null ? null : roleExtra1.trim();
    }

    public String getRoleAuthority() {
        return roleAuthority;
    }

    public void setRoleAuthority(String roleAuthority) {
        this.roleAuthority = roleAuthority == null ? null : roleAuthority.trim();
    }
}