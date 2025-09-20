package com.study.ddd.payment.application.port.out;

import com.study.ddd.payment.domain.model.entity.Payment;

public interface SavePaymentPort {
	void save(Payment payment);
}
