package com.edating.service;

import java.util.List;

import com.edating.dto.UserDto;

public interface UserService {
	public UserDto registerUser(UserDto userDto);

	public List<UserDto> getAllUsers();

	public List<UserDto> findMatches(Long userId);

}
