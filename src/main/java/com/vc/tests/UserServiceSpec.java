package com.vc.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.entities.User;
import com.vc.repositories.UserRepository;
import com.vc.services.IUserService;

public class UserServiceSpec {

	@Autowired
	private IUserService userService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getActiveUserById() {
		User user = Mockito.mock(User.class);
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		Mockito.when(user.getId()).thenReturn(1);
		Mockito.when(user.getName()).thenReturn("abc");
		Mockito.when(userRepository.findByIdAndActiveTrue(1)).thenReturn(user);

		// UserBO userBO = userService.findActiveUserById(1);

		Assert.assertTrue(1 == 0);
		// Assert.assertTrue(user.getName().equals(userBO.getName()));

	}

}
