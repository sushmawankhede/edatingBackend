package com.edating.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edating.dto.InterestDto;
import com.edating.entity.InterestEntity;
import com.edating.exceptions.InterestNotFoundException;
import com.edating.exceptions.UserNotMatchException;
import com.edating.repository.InterestRepository;
import com.edating.service.InterestService;

@Service
public class InterestServiceImpl implements InterestService {

	@Autowired
	private InterestRepository interestRepository;

	@Override
	public InterestDto updateInterest(InterestDto interestDto) {
		InterestEntity interestEntity=null;
		 interestEntity=interestRepository.findById(interestDto.getId()).orElse(null);
		if(interestEntity!=null) {
		    // because of @MapsId both will be same
			if(interestDto.getUser().getId().equals(interestEntity.getId())) {
				interestEntity.setAbout(interestDto.getAbout());
				interestEntity.setDislikes(interestDto.getDislikes());
				interestEntity.setHobbies(interestDto.getHobbies());
				interestEntity.setLikes(interestDto.getLikes());
				interestEntity.setProfileURL(interestDto.getProfileURL());
				interestRepository.save(interestEntity);
				BeanUtils.copyProperties(interestEntity, interestDto);
			}else {
				throw new UserNotMatchException("user can not update other user inerest");
			}
		}else {
			throw new InterestNotFoundException("Interest Not Found");
		}
		return interestDto;
	}

	@Override
	@Transactional
	public InterestDto deleteInterest(Long interestId) {
		InterestEntity interestEntity= interestRepository.findById(interestId).orElse(null);
		InterestDto interestDto=new InterestDto();
		
		if(interestEntity!=null) {
		    BeanUtils.copyProperties(interestEntity, interestDto);
			 interestRepository.deleteInterest(interestEntity.getId());
		}else {
			throw new InterestNotFoundException("interest not found");
		}
		return interestDto;
	}

	
}
