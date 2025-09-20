package com.study.ddd.payment.domain.model.valueobject;

import lombok.Getter;

@Getter
public enum PaymentStatus {
	REQUESTED("결제 요청됨"),
	APPROVED("결제 승인됨"),
	CANCELED("결제 취소됨");

	private final String description;

	PaymentStatus(String description) {
		this.description = description;
	}

	/** 승인 가능한 상태인지 여부 */
	public boolean canApprove() {
		return this == REQUESTED;
	}

	/** 취소 가능한 상태인지 여부 */
	public boolean canCancel() {
		return this == REQUESTED;
	}
}
