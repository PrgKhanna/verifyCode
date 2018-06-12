package com.vc.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vc.dtos.GenerateVerificationCodeResponseDTO;
import com.vc.dtos.ValidationCodeResponseDTO;
import com.vc.parsers.Parser;
import com.vc.parsers.ParserFactory;
import com.vc.services.VerificationCodeResponseGeneratorService;

@RestController
@RequestMapping("verification_code")
public class VerificationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VerificationController.class);

	@Autowired
	private VerificationCodeResponseGeneratorService verificationCodeResponseGeneratorService;

	// @RequestMapping(value = "/{userId}", method = RequestMethod.POST, produces =
	// {
	// MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	// public ResponseEntity<GenerateVerificationCodeResponseDTO>
	// generator(@PathVariable("userId") int userId) {
	// LOGGER.info("Generating Verification Code for User : " + userId);
	// GenerateVerificationCodeResponseDTO response =
	// verificationCodeResponseGeneratorService
	// .getGenerateVerificationCode(userId);
	// return new ResponseEntity<GenerateVerificationCodeResponseDTO>(response,
	// HttpStatus.OK);
	// }

	@RequestMapping(value = "/{userIdWithFormat:.+}", method = RequestMethod.POST)
	public ResponseEntity<String> generatorWithCSV(
			@Valid @NotNull @PathVariable("userIdWithFormat") String userWithFormat) {
		String[] params = userWithFormat.split("\\.");
		Integer userId = params.length > 0 ? Integer.parseInt(params[0]) : -1;
		String format = params.length > 1 ? params[1] : "json";
		LOGGER.info("Generating Verification Code for User : " + userId + " and in format : " + format);
		GenerateVerificationCodeResponseDTO responseDTO = verificationCodeResponseGeneratorService
				.getGenerateVerificationCode(userId);
		ParserFactory<GenerateVerificationCodeResponseDTO> parserFactory = new ParserFactory<GenerateVerificationCodeResponseDTO>();
		Parser<GenerateVerificationCodeResponseDTO> parser = parserFactory.getParser(format);
		return new ResponseEntity<String>(parser.parse(responseDTO), HttpStatus.OK);
	}

	// @RequestMapping(value = "/{userId}/{code}", method = RequestMethod.GET,
	// produces = {
	// MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "text/csv"
	// })
	// public ResponseEntity<ValidationCodeResponseDTO>
	// validator(@PathVariable("userId") int userId,
	// @PathVariable("code") String code) {
	// LOGGER.info("Validating Verification Code : " + code + " for User : " +
	// userId);
	// ValidationCodeResponseDTO response =
	// verificationCodeResponseGeneratorService.validatorResponse(userId, code);
	//
	// return new ResponseEntity<ValidationCodeResponseDTO>(response,
	// HttpStatus.OK);
	// }

	@RequestMapping(value = "/{userId}/{codeWithFormat:.+}", method = RequestMethod.GET)
	public ResponseEntity<String> validator(@PathVariable("userId") int userId,
			@PathVariable("codeWithFormat") String codeWithFormat) {
		String[] params = codeWithFormat.split("\\.");
		String code = params.length > 0 ? params[0] : "";
		String format = params.length > 1 ? params[1] : "json";
		LOGGER.info("Validating Verification Code : " + code + " , User : " + userId + " for format : " + format);
		ValidationCodeResponseDTO responseDTO = verificationCodeResponseGeneratorService.validatorResponse(userId,
				code);
		ParserFactory<ValidationCodeResponseDTO> parserFactory = new ParserFactory<ValidationCodeResponseDTO>();
		Parser<ValidationCodeResponseDTO> parser = parserFactory.getParser(format);
		return new ResponseEntity<String>(parser.parse(responseDTO), HttpStatus.OK);
	}

}
