package com.edating.exception;

import static com.edating.testutils.TestUtils.currentTest;
import static com.edating.testutils.TestUtils.exceptionTestFile;
import static com.edating.testutils.TestUtils.myAssert;
import static com.edating.testutils.TestUtils.testReport;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
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

import com.edating.controller.InterestController;
import com.edating.dto.InterestDto;
import com.edating.service.InterestService;
import com.edating.testutils.MasterData;

@WebMvcTest(InterestController.class)
@AutoConfigureMockMvc
public class InterestExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	
	@MockBean
	private InterestService  interestService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	 void testUpdateInterestInvalidDataException() throws Exception {
		InterestDto interestDto =MasterData.getInterestDto();
		InterestDto savedInterestDto = MasterData.getInterestDto();

		
		interestDto.setId(null);

		when(this.interestService.updateInterest(interestDto)).thenReturn(savedInterestDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/interests/update")
				.content(MasterData.asJsonString(interestDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}
	
	
}
