package com.vc.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class VerificationCodeGenerator {

	public String codeGenerator(Integer userId) {
		return RandomStringUtils.randomAlphanumeric(6);
	}

}
