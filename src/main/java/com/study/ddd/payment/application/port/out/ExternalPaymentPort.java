package com.study.ddd.payment.application.port.out;

import com.study.ddd.payment.domain.model.entity.Payment;

public interface ExternalPaymentPort {
    /**
     * 외부 결제 시스템에 결제 요청을 전송합니다.
     * 
     * @param payment 결제 정보
     * @return 결제 승인 여부
     */
    boolean processPayment(Payment payment);
    
    /**
     * 외부 결제 시스템에 결제 취소 요청을 전송합니다.
     * 
     * @param paymentId 결제 ID
     * @return 취소 성공 여부
     */
    boolean cancelPayment(String paymentId);
}
