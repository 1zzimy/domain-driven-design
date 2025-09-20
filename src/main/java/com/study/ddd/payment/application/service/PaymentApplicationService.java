package com.study.ddd.payment.application.service;

import com.study.ddd.payment.application.dto.PaymentRequest;
import com.study.ddd.payment.application.dto.PaymentResponse;
import com.study.ddd.payment.application.port.in.ApprovePaymentUseCase;
import com.study.ddd.payment.application.port.in.CanceledPaymentUseCase;
import com.study.ddd.payment.application.port.in.CreatePaymentUseCase;
import com.study.ddd.payment.application.port.out.LoadPaymentPort;
import com.study.ddd.payment.application.port.out.SavePaymentPort;
import com.study.ddd.payment.domain.model.entity.Payment;
import com.study.ddd.payment.domain.model.valueobject.Money;
import com.study.ddd.payment.domain.model.valueobject.CurrencyType;
import com.study.ddd.payment.domain.model.valueobject.PaymentMethod;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentApplicationService
	implements CreatePaymentUseCase, ApprovePaymentUseCase, CanceledPaymentUseCase {

	private final SavePaymentPort savePaymentPort;
	private final LoadPaymentPort loadPaymentPort;

	@Override
	public PaymentResponse createPayment(PaymentRequest request) {
		// DTO → Domain
		Money amount = Money.of(request.amount(), CurrencyType.valueOf(request.currency()));
		PaymentMethod method = PaymentMethod.valueOf(request.method().toUpperCase());

		Payment payment = Payment.create(amount, method);

		savePaymentPort.save(payment);

		return toResponse(payment);
	}

	@Override
	public PaymentResponse approvePayment(String paymentId) {
		Payment payment = loadPaymentPort.loadPayment(paymentId)
			.orElseThrow(() -> new IllegalArgumentException("결제를 찾을 수 없습니다: " + paymentId));

		payment.approve();
		savePaymentPort.save(payment);

		return toResponse(payment);
	}

	@Override
	public PaymentResponse cancelPayment(String paymentId) {
		Payment payment = loadPaymentPort.loadPayment(paymentId)
			.orElseThrow(() -> new IllegalArgumentException("결제를 찾을 수 없습니다: " + paymentId));

		payment.cancel();
		savePaymentPort.save(payment);

		return toResponse(payment);
	}

	private PaymentResponse toResponse(Payment payment) {
		return new PaymentResponse(
			payment.getId().toString(),
			payment.getAmount().toString(),
			payment.getMethod().name(),
			payment.getStatus().name()
		);
	}
}
