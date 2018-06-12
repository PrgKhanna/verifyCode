package com.vc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.dtos.GenerateVerificationCodeResponseDTO;
import com.vc.dtos.ValidationCodeResponseDTO;
import com.vc.models.UserBO;
import com.vc.models.UserVerificationCodeMappingBO;
import com.vc.utils.VCConstants;

@Service
public class VerificationCodeResponseGeneratorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VerificationCodeResponseGeneratorService.class);

	@Autowired
	private UserVerificationCodeMappingServiceImpl userVerificationCodeMappingService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	public GenerateVerificationCodeResponseDTO getGenerateVerificationCode(int userId) {
		GenerateVerificationCodeResponseDTO response = new GenerateVerificationCodeResponseDTO();
		try {
			// Check if user is there with this ID, if it really exists then only do
			// this else return error
			// Ideally this should be done at interceptor/authentication level

			UserBO userBO = userServiceImpl.findActiveUserById(userId);
			LOGGER.info("Got Active user by id : " + userId);

			if (null == userBO) {
				response.setResultCode(0);
				response.setMessage(VCConstants.ERROR);
			} else {
				UserVerificationCodeMappingBO userVerificationCodeMappingBO = userVerificationCodeMappingService
						.getActiveVerificationCodeForUser(userId);
				if (null != userVerificationCodeMappingBO) {
					LOGGER.info("Active Verification code already there for User:" + userId);
					response.setResultCode(1);
					response.setMessage(VCConstants.SUCCESSFUL);
				} else {
					userVerificationCodeMappingBO = userVerificationCodeMappingService
							.generateVerificationCodeForUser(userId);
					if (null != userVerificationCodeMappingBO) {
						LOGGER.info("Got newly Generated Verification code for User:" + userId);
						response.setResultCode(1);
						response.setMessage(VCConstants.SUCCESSFUL);
					} else {
						response.setResultCode(0);
						response.setMessage(VCConstants.ERROR);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Failed to Generate Verification code");
			response.setResultCode(0);
			response.setMessage(VCConstants.ERROR);
		}
		return response;
	}

	public ValidationCodeResponseDTO validatorResponse(int userId, String code) {
		ValidationCodeResponseDTO response = new ValidationCodeResponseDTO();
		try {
			UserBO userBO = userServiceImpl.findActiveUserById(userId);
			LOGGER.info("Got Active user by id : " + userId);

			if (null == userBO) {
				response.setResultCode(0);
				response.setValid("");
			} else {
				UserVerificationCodeMappingBO userVerificationCodeMappingBO = userVerificationCodeMappingService
						.getActiveVerificationCodeForUser(userId);
				if (null != userVerificationCodeMappingBO && userVerificationCodeMappingBO.getCode().equals(code)) {
					LOGGER.error("Found active Verification code : " + code + " and userId : " + userId);
					response.setResultCode(1);
					response.setValid("true");
				} else {
					LOGGER.error("No Entry for Verification code : " + code + " and userId : " + userId);
					response.setResultCode(1);
					response.setValid("false");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Failed to Generate Verification code");
			response.setResultCode(0);
			response.setValid("");
		}
		return response;
	}

}
