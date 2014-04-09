package com.at.mul.domain.order;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = { "id" })
public class Order {

	private Integer id;

	private Integer code;

	private Integer quantity;

}
