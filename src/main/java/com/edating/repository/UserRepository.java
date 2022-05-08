package com.edating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edating.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query(" from UserEntity u where u.id !=:id and u.gender !=:gender and u.age =:age and (u.city =:city OR u.country =:country)")
	List<UserEntity> findMatches(@Param("id") Long id,@Param("gender") String gender, @Param("age")Integer age, @Param("city")String city, @Param("country")String country);

	@Query("from UserEntity")
	List<UserEntity> getAllUsers();

}
