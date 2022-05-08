package com.edating.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edating.dto.InterestDto;
import com.edating.dto.UserDto;
import com.edating.entity.InterestEntity;
import com.edating.entity.UserEntity;
import com.edating.exceptions.NoMatchFoundException;
import com.edating.exceptions.UserNotFoundException;
import com.edating.repository.UserRepository;
import com.edating.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto registerUser(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		InterestEntity interestEntity=new InterestEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		BeanUtils.copyProperties(userDto.getInterest(),interestEntity);
		userEntity.setInterest(interestEntity);
		userEntity.getInterest().setUser(userEntity);
		userEntity = this.userRepository.save(userEntity);
		
		//set generated ids to response
		InterestDto interestDto=null;
		 BeanUtils.copyProperties(userEntity , userDto);
	        if(userEntity.getInterest()!=null) {
	        	interestDto=new InterestDto();
	        	BeanUtils.copyProperties(userEntity.getInterest() , interestDto);
	        	userDto.setInterest(interestDto);
	        }
		return userDto;
	}

	@Override
	@javax.transaction.Transactional
	public List<UserDto> getAllUsers() {
		List<UserEntity> userEntities= this.userRepository.getAllUsers();
		List<UserDto> userDtos= new ArrayList<>();
		if(!userEntities.isEmpty()) {
			     for (UserEntity source: userEntities ) {
			        UserDto target= new UserDto();
			        InterestDto interestDto=null;
			        BeanUtils.copyProperties(source , target);
			        Hibernate.initialize(source);
			        if(source.getInterest()!=null) {
			        	interestDto=new InterestDto();
			        	BeanUtils.copyProperties(source.getInterest() , interestDto);
			        	target.setInterest(interestDto);
			        }
			        userDtos.add(target);
			     }
		}
		return userDtos;
	}

	@Override
	public List<UserDto> findMatches(Long userId) {
		UserEntity userEntity=this.userRepository.findById(userId).orElse(null);
		List<UserEntity> userEntities=null;
		List<UserDto> userDtos= new ArrayList<>();
		if(userEntity!=null) {
			userEntities=this.userRepository.findMatches(userEntity.getId(),userEntity.getGender(),userEntity.getAge(),userEntity.getCity(),userEntity.getCountry());
			if(!userEntities.isEmpty()) {
			     for (UserEntity source: userEntities ) {
			        UserDto target= new UserDto();
			        InterestDto interestDto=null;
			        BeanUtils.copyProperties(source , target);
			        if(source.getInterest()!=null) {
			        	interestDto=new InterestDto();
			        	BeanUtils.copyProperties(source.getInterest() , interestDto);
			        	target.setInterest(interestDto);
			        }
			        userDtos.add(target);
			     }
		}else {
			throw new NoMatchFoundException("no match found");
		}
		}else {
			throw new UserNotFoundException("user not found");
		}
		
		return userDtos;
	}

}
