package com.iesports.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
@Schema(description = "Entidad que representa un rol")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID único del registro", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;

//	@Column (nullable = false)
	@Schema(description = "Nombre asociado al registro", example = "Administrador")
	private String name;
	@Schema(description = "Indica si el rol está activo (1 = sí, 0 = no)", example = "0")
	private int active;

	public Role() {

	}

	public Role(Long id, String name, int active) {
		this.id = id;
		this.name = name;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", active=" + active + "]";
	}
}
