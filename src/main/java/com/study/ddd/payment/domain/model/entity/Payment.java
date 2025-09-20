package com.study.ddd.payment.domain.model.entity;

import com.study.ddd.payment.domain.event.PaymentApprovedEvent;
import com.study.ddd.payment.domain.event.PaymentCanceledEvent;
import com.study.ddd.payment.domain.model.valueobject.CurrencyType;
import com.study.ddd.payment.domain.model.valueobject.PaymentId;
import com.study.ddd.payment.domain.model.valueobject.Money;
import com.study.ddd.payment.domain.model.valueobject.PaymentMethod;
import com.study.ddd.payment.domain.model.valueobject.PaymentStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 용
public class Payment {

	private PaymentId id;
	private Money amount;
	private PaymentMethod method;
	private PaymentStatus status;
	private LocalDateTime createdAt;

	private Payment(PaymentId id, Money amount, PaymentMethod method, PaymentStatus status) {
		this.id = id;
		this.amount = amount;
		this.method = method;
		this.status = status;
		this.createdAt = LocalDateTime.now();
	}

	// 팩토리 메서드
	public static Payment create(Money amount, PaymentMethod method) {
		return new Payment(PaymentId.newId(), amount, method, PaymentStatus.REQUESTED);
	}

	public PaymentApprovedEvent approve() {
		if (!status.canApprove()) {
			throw new IllegalStateException("결제는 REQUESTED 상태에서만 승인 가능합니다.");
		}
		this.status = PaymentStatus.APPROVED;

		return new PaymentApprovedEvent(this.id, this.amount, LocalDateTime.now());
	}

	public PaymentCanceledEvent cancel() {
		if (!status.canCancel()) {
			throw new IllegalStateException("승인된 결제는 취소할 수 없습니다.");
		}
		this.status = PaymentStatus.CANCELED;

		return new PaymentCanceledEvent(this.id, LocalDateTime.now());
	}

	public static Payment reconstruct(String id, long amount, String currency, String method, String status) {
		return new Payment(
			PaymentId.of(id),
			Money.of(amount, CurrencyType.valueOf(currency)),
			PaymentMethod.valueOf(method),
			PaymentStatus.valueOf(status)
		);
	}

}