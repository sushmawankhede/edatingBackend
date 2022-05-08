package com.edating.service;

import com.edating.dto.InterestDto;

public interface InterestService {
	public InterestDto updateInterest(InterestDto interestDto);
	public InterestDto deleteInterest(Long interestId);
}
