package com.study.ddd.payment.domain.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public final class PaymentId {
	private final UUID value;

	private PaymentId(UUID value) {
		this.value = Objects.requireNonNull(value);
	}

	public static PaymentId newId() {
		return new PaymentId(UUID.randomUUID());
	}

	public static PaymentId of(UUID value) {
		return new PaymentId(value);
	}

	public static PaymentId of(String value) {
		return new PaymentId(UUID.fromString(value));
	}

	public UUID getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PaymentId other)) return false;
		return value.equals(other.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return value.toString();
	}
}