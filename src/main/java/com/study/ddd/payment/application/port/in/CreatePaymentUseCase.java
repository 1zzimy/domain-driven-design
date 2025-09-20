package com.study.ddd.payment.application.port.in;

import com.study.ddd.payment.application.dto.PaymentRequest;
import com.study.ddd.payment.application.dto.PaymentResponse;

public interface CreatePaymentUseCase {
	PaymentResponse createPayment(PaymentRequest request);
}
