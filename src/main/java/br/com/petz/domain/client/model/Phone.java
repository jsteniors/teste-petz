package br.com.petz.domain.client.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class Phone {
	
	@NotNull @Min(0) @Max(99)
	private Integer prefix;
	@NotNull @Min(0) @Max(999999999)
	private Long number;
	
	public Phone() {}
	
	public Phone(Integer prefix, Long number) {
		this.prefix = prefix;
		this.number = number;
	}

	public Integer getPrefix() {
		return prefix;
	}

	public Long getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "Phone [prefix=" + prefix + ", number=" + number + "]";
	}
	
}
