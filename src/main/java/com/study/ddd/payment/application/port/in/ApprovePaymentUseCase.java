package com.study.ddd.payment.application.port.in;

import com.study.ddd.payment.application.dto.PaymentResponse;

public interface ApprovePaymentUseCase {
	PaymentResponse approvePayment(String paymentId);
}
