package com.study.ddd.payment.application.port.out;

import java.util.Optional;

import com.study.ddd.payment.domain.model.entity.Payment;

public interface LoadPaymentPort {
	Optional<Payment> loadPayment(String paymentId);
}
