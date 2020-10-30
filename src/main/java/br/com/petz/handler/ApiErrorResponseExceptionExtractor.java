package br.com.petz.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.petz.handler.model.ApiErrorResponse;
import br.com.petz.handler.model.FormErro;

@Component
public class ApiErrorResponseExceptionExtractor {
	public ApiErrorResponse getApiResponse(Exception e, long defaultCode) {
		return ApiErrorResponse.builder()
				.description(e.getMessage())
				.code(defaultCode)
				.message(e.getLocalizedMessage())
				.build()
				.createErros(e);
	}
	public ApiErrorResponse getApiResponse(List<FormErro> listFormErros) {
		return ApiErrorResponse.builder()
				.description("FORM ERRORS")
				.code(400l)
				.message("ERROR TO VALID FORM")
				.validationsErrors(listFormErros)
				.build();
	}
}
