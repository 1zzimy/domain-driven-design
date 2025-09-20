package com.study.ddd.payment.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.ddd.payment.application.dto.PaymentRequest;
import com.study.ddd.payment.application.dto.PaymentResponse;
import com.study.ddd.payment.application.port.in.ApprovePaymentUseCase;
import com.study.ddd.payment.application.port.in.CanceledPaymentUseCase;
import com.study.ddd.payment.application.port.in.CreatePaymentUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/payments")
@RequiredArgsConstructor
public class PaymentController {

	private final CreatePaymentUseCase createPaymentUseCase;
	private final ApprovePaymentUseCase approvePaymentUseCase;
	private final CanceledPaymentUseCase cancelPaymentUseCase;

	@PostMapping
	public ResponseEntity<PaymentResponse> create(@RequestBody PaymentRequest request) {
		return ResponseEntity.ok(createPaymentUseCase.createPayment(request));
	}

	@PostMapping("/{id}/approve")
	public ResponseEntity<PaymentResponse> approve(@PathVariable String id) {
		return ResponseEntity.ok(approvePaymentUseCase.approvePayment(id));
	}

	@PostMapping("/{id}/cancel")
	public ResponseEntity<PaymentResponse> cancel(@PathVariable String id) {
		return ResponseEntity.ok(cancelPaymentUseCase.cancelPayment(id));
	}

}
