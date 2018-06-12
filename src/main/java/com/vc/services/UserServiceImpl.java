package com.vc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.mappers.ObjectMapperService;
import com.vc.models.UserBO;
import com.vc.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapperService mapper;

	@Override
	public UserBO findActiveUserById(Integer id) {
		LOGGER.info("getting active user by id : " + id);
		return mapper.map(userRepository.findByIdAndActiveTrue(id), UserBO.class);
	}

}
