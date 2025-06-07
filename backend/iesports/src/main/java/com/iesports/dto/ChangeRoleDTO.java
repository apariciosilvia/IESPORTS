package com.iesports.dto;

import jakarta.validation.constraints.NotNull;

public class ChangeRoleDTO {
	@NotNull(message = "El nombre es obligatorio")
    private Long personId;

    @NotNull(message = "El nombre es obligatorio")
    private Long roleId;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

	@Override
	public String toString() {
		return "ChangeRoleDTO [personId=" + personId + ", roleId=" + roleId + "]";
	}
    
}
