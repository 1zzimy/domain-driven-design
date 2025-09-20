package com.study.ddd.payment.domain.event;

import java.time.LocalDateTime;

import com.study.ddd.payment.domain.model.valueobject.PaymentId;

public record PaymentCanceledEvent(
	PaymentId paymentId,
	LocalDateTime canceledAt
) {}
