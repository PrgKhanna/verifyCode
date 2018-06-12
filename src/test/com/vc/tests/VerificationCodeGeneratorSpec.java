package com.vc.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.stereotype.Component;

import com.vc.utils.VerificationCodeGenerator;

public class VerificationCodeGeneratorSpec {

	private VerificationCodeGenerator codeGenerator;

	@Before
	public void init() {

	}

	@Test
	public void checkMockedCodeGenerator() {
		codeGenerator = Mockito.mock(VerificationCodeGenerator.class);
		Mockito.when(codeGenerator.codeGenerator(1)).thenReturn("aaaaaa");
		String code = codeGenerator.codeGenerator(1);
		assertEquals("aaaaaa", code);
	}

	@Test
	public void checkCodeGenerator() {
		codeGenerator = new VerificationCodeGenerator();
		String code = codeGenerator.codeGenerator(1);
		assertNotNull(code);
	}

}
