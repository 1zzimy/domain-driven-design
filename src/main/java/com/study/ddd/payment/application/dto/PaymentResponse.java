package com.study.ddd.payment.application.dto;

public record PaymentResponse(
	String paymentId,
	String amount,
	String method,
	String status
) {}