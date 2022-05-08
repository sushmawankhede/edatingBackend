package com.edating.exception;

import static com.edating.testutils.TestUtils.businessTestFile;
import static com.edating.testutils.TestUtils.currentTest;
import static com.edating.testutils.TestUtils.exceptionTestFile;
import static com.edating.testutils.TestUtils.myAssert;
import static com.edating.testutils.TestUtils.testReport;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.edating.controller.UserController;
import com.edating.dto.UserDto;
import com.edating.exceptions.InterestNotFoundException;
import com.edating.exceptions.UserNotFoundException;
import com.edating.model.exception.ExceptionResponse;
import com.edating.service.InterestService;
import com.edating.service.UserService;
import com.edating.testutils.MasterData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@MockBean
	private InterestService  interestService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	 void testRegisterUserInvalidDataException() throws Exception {
		UserDto userDto =MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();

		savedUserDto.setId(1L);
		userDto.setUsername("Ab");

		when(this.userService.registerUser(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register-user")
				.content(MasterData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}
	
	@Test
	 void testWhileMachingUserNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("User with Id - 10001 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.userService.findMatches(10001L)).thenThrow(new UserNotFoundException("User with Id - 10001 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/matches/10001")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
	
	
	@Test
	 void testInterestNotFoundWhileDeletingException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Interest with Id - 10001 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.interestService.deleteInterest(10001L)).thenThrow(new InterestNotFoundException("Interest with Id - 10001 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/delete/10001")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
	
}
