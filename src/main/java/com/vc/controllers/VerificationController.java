package com.vc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vc.dtos.GenerateVerificationCodeResponseDTO;
import com.vc.dtos.ValidationCodeResponseDTO;
import com.vc.services.VerificationCodeResponseGeneratorService;

@RestController
@RequestMapping("verification_code")
public class VerificationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VerificationController.class);

	@Autowired
	private VerificationCodeResponseGeneratorService verificationCodeResponseGeneratorService;

	// @Autowired
	// private ResponseToCSV responseToCsv;

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "text/csv" })
	public ResponseEntity<GenerateVerificationCodeResponseDTO> generator(@PathVariable("userId") int userId) {
		LOGGER.info("Generating Verification Code for User : " + userId);
		GenerateVerificationCodeResponseDTO response = verificationCodeResponseGeneratorService
				.getGenerateVerificationCode(userId);
		return new ResponseEntity<GenerateVerificationCodeResponseDTO>(response, HttpStatus.OK);
	}

	// FOR CSV Format
	// @RequestMapping(value = "/{userId}", method = RequestMethod.POST, produces =
	// { "text/csv" })
	// public void generatorAsCSV(@PathVariable("userId") int userId,
	// HttpServletResponse httpResponse)
	// throws IOException {
	// LOGGER.info("Generating Verification Code for User : " + userId);
	// GenerateVerificationCodeResponseDTO responseDTO =
	// verificationCodeResponseGeneratorService
	// .getGenerateVerificationCode(userId);
	// responseToCsv.writeVerificationCodeToCsv(httpResponse.getWriter(),
	// responseDTO);
	// }

	@RequestMapping(value = "/{userId}/{code}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "text/csv" })
	public ResponseEntity<ValidationCodeResponseDTO> validator(@PathVariable("userId") int userId,
			@PathVariable("code") String code) {
		LOGGER.info("Validating Verification Code : " + code + " for User : " + userId);
		ValidationCodeResponseDTO response = verificationCodeResponseGeneratorService.validatorResponse(userId, code);

		return new ResponseEntity<ValidationCodeResponseDTO>(response, HttpStatus.OK);
	}

}
