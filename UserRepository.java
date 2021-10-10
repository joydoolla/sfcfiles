package com.sfc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sfc.model.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

	@Query("select u from Users u where u.status = ?1 and (u.userName = ?2 or u.mobileNumber = ?3) ")
	Users findUserDeails(String status, String userName, String mobileNumber);

	Users findByUserId(int userId);

	@Query("select count(e)>0 from Users e where e.userName = ?1 or e.mobileNumber = ?1 ")
	public boolean userExists(String param);
}
