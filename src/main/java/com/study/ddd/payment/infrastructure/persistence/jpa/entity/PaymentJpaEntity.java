package com.study.ddd.payment.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentJpaEntity {

	@Id
	private String id;

	private long amount;

	private String currency;

	private String method;

	private String status;

	public static PaymentJpaEntity fromDomain(com.study.ddd.payment.domain.model.entity.Payment payment) {
		return PaymentJpaEntity.builder()
			.id(payment.getId().toString())
			.amount(payment.getAmount().amount().longValue())
			.currency(payment.getAmount().currency().name())
			.method(payment.getMethod().name())
			.status(payment.getStatus().name())
			.build();
	}

	public com.study.ddd.payment.domain.model.entity.Payment toDomain() {
		return com.study.ddd.payment.domain.model.entity.Payment.reconstruct(
			this.id,
			this.amount,
			this.currency,
			this.method,
			this.status
		);
	}
}
