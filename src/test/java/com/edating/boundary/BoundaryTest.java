package com.edating.boundary;

import static com.edating.testutils.TestUtils.boundaryTestFile;
import static com.edating.testutils.TestUtils.currentTest;
import static com.edating.testutils.TestUtils.myAssert;
import static com.edating.testutils.TestUtils.testReport;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.edating.dto.UserDto;
import com.edating.testutils.MasterData;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {
	private static Validator validator;

	// ----------------------------------------------------------------------------------------------
	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}
	
	@Test
	 void testUsernameNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setUsername(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testUsernameMin5() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setUsername("abs");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testUsernameMax30() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setUsername("wertghdfgbnhfghnhjmkghjklkjhgfff");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testPasswordNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setPassword(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testPasswordMin5() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setPassword("abs");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testPasswordMax30() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setPassword("wertghdfgbnhfghnhjmkghjklkjhgfff");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testAgeNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setAge(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	@Test
	 void testAgeMin18() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setAge(17);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testAgeMax45() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setAge(56);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testMailNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setEmail(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testMailPattern() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setEmail("asdf@@gmail.com");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testPhoneNumberNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setPhoneNumber(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testPhoneNumberMin10() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setPhoneNumber("123432345");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	 void testPhoneNumberMax10() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setPhoneNumber("34565434567");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	

}
