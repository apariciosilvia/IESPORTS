package com.iesports.dto;

import jakarta.validation.constraints.NotNull;

public class ChangeRoleAndCourseDTO {
	
	@NotNull(message = "El usuario es obligatorio")
	private Long personId;

	@NotNull(message = "El rol es obligatorio")
	private Long roleId;

	@NotNull(message = "El curso es obligatorio")
	private Long courseId;

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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "ChangeRoleAndCourseDTO [personId=" + personId + ", roleId=" + roleId + ", courseId=" + courseId + "]";
	}

}
