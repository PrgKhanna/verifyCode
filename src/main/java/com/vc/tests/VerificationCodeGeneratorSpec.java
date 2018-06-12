package com.vc.tests;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.vc.utils.VerificationCodeGenerator;

public class VerificationCodeGeneratorSpec {

	@Test
	public void verifyCodeGenerator() {
		RandomStringUtils util = Mockito.mock(RandomStringUtils.class);
		Mockito.when(util.randomAlphanumeric(6)).thenReturn("aaaaaa");
		VerificationCodeGenerator gen = new VerificationCodeGenerator();
		String a = gen.codeGenerator(1);

		Assert.assertTrue(a.equals("aaaaaa"));
	}

}
