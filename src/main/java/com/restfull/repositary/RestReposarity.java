package com.restfull.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restfull.entity.RegistrationEntity;


@Repository
public interface RestReposarity extends JpaRepository<RegistrationEntity , String> {

//	@Query(name = "select id from registration where gmail=?1",nativeQuery = true)
	RegistrationEntity findByGmail(String gmail);

	Boolean existsByGmail(String gmail);
}
