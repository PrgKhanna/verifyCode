package com.vc.models;

import java.io.Serializable;
import java.util.Date;

public class UserBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String email;

	private String phone;

	private Date createdOn;

	private Date updatedOn;

	private Boolean active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserBO [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", createdOn="
				+ createdOn + ", updatedOn=" + updatedOn + ", active=" + active + "]";
	}

}
