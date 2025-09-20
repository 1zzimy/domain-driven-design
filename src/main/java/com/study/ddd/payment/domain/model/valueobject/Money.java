package com.study.ddd.payment.domain.model.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 금액(Value Object)
 * - record로 구현하여 불변성 자동 확보
 * - 생성자에서 유효성 검증
 */
public record Money(BigDecimal amount, CurrencyType currency) {

	public Money {
		Objects.requireNonNull(currency, "통화(currency)는 필수 값입니다.");
		Objects.requireNonNull(amount, "금액(amount)은 필수 값입니다.");

		if (amount.signum() < 0) {
			throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
		}

		// 통화 단위별 소수점 자리수에 맞게 스케일 조정
		amount = amount.setScale(currency.getScale(), RoundingMode.HALF_UP);
	}

	public static Money of(BigDecimal amount, CurrencyType currency) {
		return new Money(amount, currency);
	}

	public static Money of(long amount, CurrencyType currency) {
		return new Money(BigDecimal.valueOf(amount), currency);
	}

	public Money plus(Money other) {
		validateSameCurrency(other);
		return new Money(this.amount.add(other.amount), currency);
	}

	public Money minus(Money other) {
		validateSameCurrency(other);
		if (this.amount.compareTo(other.amount) < 0) {
			throw new IllegalArgumentException("차감할 금액이 부족합니다.");
		}
		return new Money(this.amount.subtract(other.amount), currency);
	}

	public Money multiply(int factor) {
		return new Money(this.amount.multiply(BigDecimal.valueOf(factor)), currency);
	}

	private void validateSameCurrency(Money other) {
		if (!this.currency.equals(other.currency)) {
			throw new IllegalArgumentException("통화 단위가 일치하지 않습니다.");
		}
	}
}