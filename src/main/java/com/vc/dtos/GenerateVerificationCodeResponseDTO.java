package com.vc.dtos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.NONE)
@JsonTypeName(value = "response")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonPropertyOrder({ "result_code", "message" })
public class GenerateVerificationCodeResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "result_code")
	@JsonProperty(value = "result_code")
	private int resultCode;

	@XmlElement
	private String message;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
