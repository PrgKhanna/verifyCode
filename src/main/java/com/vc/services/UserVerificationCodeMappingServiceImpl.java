package com.vc.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.entities.UserVerificationCodeMapping;
import com.vc.mappers.ObjectMapperService;
import com.vc.models.UserVerificationCodeMappingBO;
import com.vc.repositories.UserVerificationCodeMappingRepository;
import com.vc.utils.VerificationCodeGenerator;

@Service
public class UserVerificationCodeMappingServiceImpl implements IUserVerificationCodeMappingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserVerificationCodeMappingServiceImpl.class);

	@Autowired
	private ObjectMapperService mapper;

	@Autowired
	private UserVerificationCodeMappingRepository userVerificationCodeMappingRepository;

	@Autowired
	private VerificationCodeGenerator verificationCodeGenerator;

	@Override
	public UserVerificationCodeMappingBO getActiveVerificationCodeForUser(Integer userId) {
		LOGGER.info("getting active verification code for user : " + userId);
		return mapper.map(userVerificationCodeMappingRepository.findByUserIdAndActiveTrue(userId),
				UserVerificationCodeMappingBO.class);
	}

	public UserVerificationCodeMappingBO generateVerificationCodeForUser(Integer userId) {
		UserVerificationCodeMapping userVerificationCodeMapping = new UserVerificationCodeMapping();
		userVerificationCodeMapping.setUserId(userId);
		userVerificationCodeMapping.setCode(verificationCodeGenerator.codeGenerator(userId));
		userVerificationCodeMapping.setActive(true);
		try {
			userVerificationCodeMappingRepository.save(userVerificationCodeMapping);
			return mapper.map(userVerificationCodeMapping, UserVerificationCodeMappingBO.class);
		} catch (Exception e) {
			LOGGER.error("Failed to save Verification code for user : " + userId);
		}
		return null;
	}

	@Override
	public List<UserVerificationCodeMappingBO> getAllActiveMappings() {
		return mapper.mapAsList(userVerificationCodeMappingRepository.findByActiveTrue(),
				UserVerificationCodeMappingBO.class);
	}

	public void updateUserVerificationCodeMappings(List<UserVerificationCodeMappingBO> expiredMappingBOs) {
		List<UserVerificationCodeMapping> mappings = mapper.mapAsList(expiredMappingBOs,
				UserVerificationCodeMapping.class);
		userVerificationCodeMappingRepository.save(mappings);
	}

}
