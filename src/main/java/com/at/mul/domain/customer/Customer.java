package com.at.mul.domain.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = { "id" })
public class Customer {

	private Integer id;

	private String name;

	private Integer age;

}
