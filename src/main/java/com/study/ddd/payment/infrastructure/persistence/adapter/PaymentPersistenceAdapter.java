package com.study.ddd.payment.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.study.ddd.payment.application.port.out.LoadPaymentPort;
import com.study.ddd.payment.application.port.out.SavePaymentPort;
import com.study.ddd.payment.domain.model.entity.Payment;
import com.study.ddd.payment.infrastructure.persistence.jpa.entity.PaymentJpaEntity;
import com.study.ddd.payment.infrastructure.persistence.jpa.repository.PaymentJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements SavePaymentPort, LoadPaymentPort {

	private final PaymentJpaRepository repository;

	@Override
	public Optional<Payment> loadPayment(String paymentId) {
		return repository.findById(paymentId)
			.map(PaymentJpaEntity::toDomain);
	}

	@Override
	public void save(Payment payment) {
		repository.save(PaymentJpaEntity.fromDomain(payment));
	}
}
