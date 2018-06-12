package com.vc.services;

import com.vc.models.UserBO;

public interface IUserService {

	UserBO findActiveUserById(Integer id);

}
