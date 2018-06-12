package com.vc.services;

import java.util.List;

import com.vc.models.UserVerificationCodeMappingBO;

public interface IUserVerificationCodeMappingService {

	UserVerificationCodeMappingBO getActiveVerificationCodeForUser(Integer userId);

	UserVerificationCodeMappingBO generateVerificationCodeForUser(Integer userId);

	List<UserVerificationCodeMappingBO> getAllActiveMappings();

	void updateUserVerificationCodeMappings(List<UserVerificationCodeMappingBO> mappingBOs);

}
