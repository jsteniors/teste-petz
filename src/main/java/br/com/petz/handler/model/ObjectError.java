package br.com.petz.handler.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Error Enitity")
public class ObjectError   {
	
	@ApiModelProperty(value = "Error ID")
	private Long code;
	@ApiModelProperty(value = "Error Description")
	private String message;
	@ApiModelProperty(value = "Native Error description")
	private String nativeMessage;
	
	public ObjectError(Long code, String message, String nativeMessage) {
		this.code = code;
		this.message = message;
		this.nativeMessage = nativeMessage;
	}
	
	public Long getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public String getNativeMessage() {
		return nativeMessage;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectError other = (ObjectError) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ObjectError [code=" + code + ", message=" + message + ", nativeMessage=" + nativeMessage + "]";
	}
	
}

