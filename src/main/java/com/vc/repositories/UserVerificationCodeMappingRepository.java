package com.vc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vc.entities.UserVerificationCodeMapping;

@Repository
public interface UserVerificationCodeMappingRepository extends CrudRepository<UserVerificationCodeMapping, Integer> {

	UserVerificationCodeMapping findByUserIdAndActiveTrue(Integer userId);

	UserVerificationCodeMapping findByUserIdAndCodeAndActiveTrue(Integer userId, String code);

	List<UserVerificationCodeMapping> findByActiveTrue();

}
