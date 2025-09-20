package com.study.ddd.payment.application.dto;

public record PaymentRequest(
	long amount,
	String method,
	String currency
) {}