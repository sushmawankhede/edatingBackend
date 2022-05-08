package com.edating.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edating.dto.InterestDto;
import com.edating.exceptions.InvalidDataException;
import com.edating.service.InterestService;

@RestController
@RequestMapping("/api/v1/interests")
public class InterestController {

	@Autowired
	private InterestService interestService;
	
	@PutMapping("/update")
	public ResponseEntity<InterestDto> updateInterest(@Valid @RequestBody InterestDto interestDto, BindingResult result) {
        if (result.hasErrors()||interestDto==null||interestDto.getId()==null) {
			throw new InvalidDataException("interest data is not Valid!");
        }
        interestDto = this.interestService.updateInterest(interestDto);
        return ResponseEntity.ok(interestDto);
	}

}
