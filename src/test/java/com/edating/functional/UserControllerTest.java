package com.edating.functional;

import static com.edating.testutils.TestUtils.businessTestFile;
import static com.edating.testutils.TestUtils.currentTest;
import static com.edating.testutils.TestUtils.myAssert;
import static com.edating.testutils.TestUtils.testReport;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.edating.controller.UserController;
import com.edating.dto.InterestDto;
import com.edating.dto.UserDto;
import com.edating.service.InterestService;
import com.edating.service.UserService;
import com.edating.testutils.MasterData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@MockBean
	private InterestService interestService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	 void testRegisterUser() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		userDto.setId(null);
		when(this.userService.registerUser(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register-user")
				.content(MasterData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDto))
					? "true"
					: "false"),
				businessTestFile);

	}
	
	@Test
	 void testGetAllUser() throws Exception {
		List<UserDto> savedUserDtos = MasterData.getUserDtoList();
		when(this.userService.getAllUsers()).thenReturn(savedUserDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/get/all")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDtos))
					? "true"
					: "false"),
				businessTestFile);

	}

	
	@Test
	 void testRegisterUserIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setId(1L);

		when(this.userService.registerUser(userDto)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return savedUserDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register-user")
				.content(MasterData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	
	@Test
	 void testGetAllUserIsServiceMethodCalled() throws Exception {
	    final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		List<UserDto> savedUserDto = MasterData.getUserDtoList();

		when(this.userService.getAllUsers()).then(new Answer<List<UserDto>>() {

			@Override
			public List<UserDto> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return savedUserDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/get/all")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	
	@Test
	 void testFindMaches() throws Exception {
	    List<UserDto> savedUserDto = MasterData.getUserDtoList();
		when(this.userService.findMatches(1L)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/matches/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDto))
					? "true"
					: "false"),
				businessTestFile);

	}
	
	@Test
	 void testFindMachesIsServiceMethodCalled() throws Exception {
	    final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		List<UserDto> savedUserDto = MasterData.getUserDtoList();

		when(this.userService.findMatches(1L)).then(new Answer<List<UserDto>>() {

			@Override
			public List<UserDto> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return savedUserDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/matches/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	
	@Test
	 void testDeleteIneterest() throws Exception {
	    InterestDto savedUserDto = MasterData.getInterestDto();
		when(this.interestService.deleteInterest(1L)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/delete/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDto))
					? "true"
					: "false"),
				businessTestFile);

	}
	
	@Test
	 void testDeleteIneterestIsServiceMethodCalled() throws Exception {
	    final int count[] = new int[1];
		InterestDto interestDto = MasterData.getInterestDto();

		when(this.interestService.deleteInterest(1L)).then(new Answer<InterestDto>() {

			@Override
			public InterestDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return interestDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/delete/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	

}
