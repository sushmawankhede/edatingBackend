package com.edating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edating.entity.InterestEntity;

@Repository
public interface InterestRepository extends JpaRepository<InterestEntity, Long> {

	@Modifying
	@Query("delete from InterestEntity i where i.id =:interestEntityId ")
	void deleteInterest(@Param("interestEntityId")Long interestEntityId);

}
