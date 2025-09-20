package com.study.ddd.payment.domain.model.valueobject;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum PaymentMethod {
	CARD("신용 카드"),
	PAY("간편 결제");

	private final String description;

	PaymentMethod(String description) {
		this.description = description;
	}
}
