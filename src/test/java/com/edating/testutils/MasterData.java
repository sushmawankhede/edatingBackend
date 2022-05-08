package com.edating.testutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.edating.dto.InterestDto;
import com.edating.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MasterData {


	public static UserDto getUserDto() {
		UserDto userDto = new UserDto();
		userDto.setAge(21);
		userDto.setCity("hyderabad");
		userDto.setCountry("india");
		userDto.setEmail("myemail@gmail.com");
		userDto.setGender("male");
		userDto.setId(1L);
		userDto.setInterest(getInterestDto());
		userDto.setPassword("1234564678");
		userDto.setPhoneNumber("7788034523");
		userDto.setUsername("myusername");
		return userDto;
	}
	
	public static InterestDto getInterestDto() {
		InterestDto interestDto = new InterestDto();
		interestDto.setAbout("my about is");
		interestDto.setDislikes("mydislike");
		interestDto.setHobbies(Arrays.asList("hobby1","hobby2"));
		interestDto.setLikes("safsfd");
		interestDto.setProfileURL("https://localhost:8080/me");
		return interestDto;
	}
	
	
	public static List<UserDto> getUserDtoList() {
		List<UserDto> userDtos = new ArrayList<>();
		UserDto userDto = getUserDto();
		userDtos.add(userDto);
		userDto = getUserDto();
		userDto.setId(2L);
		userDto.setUsername("asdfafd");;
		userDtos.add(userDto);
		return userDtos;
	
	}
	

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
