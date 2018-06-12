package com.vc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vc.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByIdAndActiveTrue(Integer id);

}
