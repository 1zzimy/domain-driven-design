package com.study.ddd.payment.domain.model.valueobject;

import lombok.Getter;

/**
 * 지원 통화 단위 (DDD 도메인 언어 기반)
 */
@Getter
public enum CurrencyType {
	KRW("한국 원화", 0),
	USD("미국 달러", 2),
	EUR("유로", 2),
	JPY("일본 엔화", 0),
	CNY("중국 위안화", 2);

	private final String description;
	private final int scale; // 소수점 자리수

	CurrencyType(String description, int scale) {
		this.description = description;
		this.scale = scale;
	}

}
