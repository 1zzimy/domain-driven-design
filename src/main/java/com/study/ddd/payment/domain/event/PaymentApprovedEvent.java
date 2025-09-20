package com.study.ddd.payment.domain.event;

import java.time.LocalDateTime;

import com.study.ddd.payment.domain.model.valueobject.Money;
import com.study.ddd.payment.domain.model.valueobject.PaymentId;

public record PaymentApprovedEvent(
	PaymentId paymentId,
	Money amount,
	LocalDateTime approvedAt
) {}
