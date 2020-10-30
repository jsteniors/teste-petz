package br.com.petz.handler.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Error API responses")
@JsonInclude(Include.NON_NULL)
public class ApiErrorResponse {
	
	@ApiModelProperty(value = "Error ID")
	private Long code;
	@ApiModelProperty(value = "Error Description")
	private String message;
	@ApiModelProperty(value = "Error Details")
	private String description;
	@JsonProperty("errors")
	private List<ObjectError> objectErrors;
	@JsonProperty("validations errors")
	private List<FormErro> validationsErrors;
	
	private ApiErrorResponse() {}
	
	public ApiErrorResponse addErrorsItem(ObjectError errorsItem) {
		this.objectErrors = Optional.ofNullable(this.objectErrors).orElse(new ArrayList<>());
		this.objectErrors.add(errorsItem);
		return this;
	}

	public ApiErrorResponse createErros(Exception e) {
		return addErrorsItem(buildObjectError(e));
	}

	private ObjectError buildObjectError(Exception e) {
		return new ObjectError(this.getCode(), e.getMessage(), getNativeMessage(e));
	}

	private String getNativeMessage(Exception e) {
		return Optional.ofNullable(e.getCause())
				.map(Throwable::getMessage)
				.orElse(e.getLocalizedMessage());
	}

	public Long getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

	public List<ObjectError> getObjectErrors() {
		return objectErrors;
	}

	public List<FormErro> getValidationsErrors() {
		return validationsErrors;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private ApiErrorResponse apiErrorResponse;
		
		public Builder() {
			this.apiErrorResponse = new ApiErrorResponse();
		}

		public Builder code(Long code) {
			this.apiErrorResponse.code = code;
			return this;
		}
		
		public Builder message(String message) {
			this.apiErrorResponse.message = message;
			return this;
		}

		public Builder description(String description) {
			this.apiErrorResponse.description = description;
			return this;
		}
		
		public Builder validationsErrors(List<FormErro> validationsErrors) {
			this.apiErrorResponse.validationsErrors = validationsErrors;
			return this;
		}
		
		public ApiErrorResponse build() {
			return this.apiErrorResponse;
		}
		
	}
}
