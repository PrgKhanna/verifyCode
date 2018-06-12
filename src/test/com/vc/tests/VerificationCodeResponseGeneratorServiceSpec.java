package com.vc.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.vc.dtos.GenerateVerificationCodeResponseDTO;
import com.vc.dtos.ValidationCodeResponseDTO;
import com.vc.models.UserBO;
import com.vc.models.UserVerificationCodeMappingBO;
import com.vc.services.UserServiceImpl;
import com.vc.services.UserVerificationCodeMappingServiceImpl;
import com.vc.services.VerificationCodeResponseGeneratorService;

public class VerificationCodeResponseGeneratorServiceSpec {

	@Mock
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserVerificationCodeMappingServiceImpl userVerificationCodeMappingServiceImpl;

	@Spy
	@InjectMocks
	private VerificationCodeResponseGeneratorService service = new VerificationCodeResponseGeneratorService();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGenerateVerificationCodeWithUserNull() {
		Mockito.when(userServiceImpl.findActiveUserById(1)).thenReturn(null);

		GenerateVerificationCodeResponseDTO response = service.getGenerateVerificationCode(1);

		assertNotNull(response);
		assertTrue(response.getResultCode() == 0);
		assertTrue(response.getMessage() == "error");
	}

	@Test
	public void testGenerateVerificationCodeWithUser() {
		UserBO userBO = new UserBO();
		userBO.setId(1);

		UserVerificationCodeMappingBO mappingBO = new UserVerificationCodeMappingBO();
		mappingBO.setId(1);
		mappingBO.setActive(true);
		mappingBO.setCode("aaaaaa");
		Mockito.when(userServiceImpl.findActiveUserById(1)).thenReturn(userBO);
		Mockito.when(userVerificationCodeMappingServiceImpl.getActiveVerificationCodeForUser(1)).thenReturn(mappingBO);

		GenerateVerificationCodeResponseDTO response = service.getGenerateVerificationCode(1);

		assertNotNull(response);
		assertTrue(response.getResultCode() == 1);
		assertTrue(response.getMessage() == "successful");
	}

	@Test
	public void TestValidatorResponseWithUserNull() {
		Mockito.when(userServiceImpl.findActiveUserById(1)).thenReturn(null);

		ValidationCodeResponseDTO response = service.validatorResponse(1, "aaaaaa");

		assertNotNull(response);
		assertTrue(response.getResultCode() == 0);
		assertTrue(response.getValid() == "");
	}
	
	@Test
	public void TestValidatorResponseWithUserAndCodeNull() {
		UserBO userBO = new UserBO();
		userBO.setId(1);
		Mockito.when(userServiceImpl.findActiveUserById(1)).thenReturn(userBO);
		Mockito.when(userVerificationCodeMappingServiceImpl.getActiveVerificationCodeForUser(1)).thenReturn(null);

		ValidationCodeResponseDTO response = service.validatorResponse(1, "aaaaaa");

		assertNotNull(response);
		assertTrue(response.getResultCode() == 1);
		assertTrue(response.getValid() == "false");
	}
	
	@Test
	public void TestValidatorResponseWithUserAndCode() {
		UserBO userBO = new UserBO();
		userBO.setId(1);
		
		UserVerificationCodeMappingBO mappingBO = new UserVerificationCodeMappingBO();
		mappingBO.setId(1);
		mappingBO.setActive(true);
		mappingBO.setCode("aaaaaa");
		Mockito.when(userServiceImpl.findActiveUserById(1)).thenReturn(userBO);
		Mockito.when(userVerificationCodeMappingServiceImpl.getActiveVerificationCodeForUser(1)).thenReturn(mappingBO);

		ValidationCodeResponseDTO response = service.validatorResponse(1, "aaaaaa");

		assertNotNull(response);
		assertTrue(response.getResultCode() == 1);
		assertTrue(response.getValid() == "true");
	}

}
