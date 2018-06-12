package com.vc.models;

import java.io.Serializable;
import java.util.Date;

public class UserVerificationCodeMappingBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer userId;

	private String code;

	private Boolean active;

	private Date createdOn;

	private Date updatedOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
		return "UserVerificationCodeMappingBO [id=" + id + ", userId=" + userId + ", code=" + code + ", active="
				+ active + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}

}
