package com.edating.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edating.dto.InterestDto;
import com.edating.dto.UserDto;
import com.edating.exceptions.InvalidDataException;
import com.edating.service.InterestService;
import com.edating.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private InterestService interestService;
	
	@PostMapping("/register-user")
	public ResponseEntity<UserDto> registerCustomer(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
			throw new InvalidDataException("User data is not Valid!");
        }
        userDto = this.userService.registerUser(userDto);
        return ResponseEntity.ok(userDto);
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = this.userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/matches/{userId}")
	public ResponseEntity<List<UserDto>> findMatches(@PathVariable("userId") Long userId) {
        List<UserDto> users = this.userService.findMatches(userId);
		return ResponseEntity.ok(users);
	}
	
	@DeleteMapping("delete/{interestId}")
	public ResponseEntity<InterestDto> deleteInterest(@PathVariable("interestId") Long interestId) {
       InterestDto interestDto = this.interestService.deleteInterest(interestId);
		return ResponseEntity.ok(interestDto);
	}

}
