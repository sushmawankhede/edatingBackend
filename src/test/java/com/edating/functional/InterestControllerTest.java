package com.edating.functional;

import static com.edating.testutils.TestUtils.businessTestFile;
import static com.edating.testutils.TestUtils.currentTest;
import static com.edating.testutils.TestUtils.myAssert;
import static com.edating.testutils.TestUtils.testReport;
import static org.mockito.Mockito.when;

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

import com.edating.controller.InterestController;
import com.edating.dto.InterestDto;
import com.edating.service.InterestService;
import com.edating.service.UserService;
import com.edating.testutils.MasterData;

@WebMvcTest(InterestController.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class InterestControllerTest {
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
     void testUpdateInterest() throws Exception {
	InterestDto interestDto = MasterData.getInterestDto();
	InterestDto savedInterestDto = MasterData.getInterestDto();
	savedInterestDto.setId(1L);
	interestDto.setId(1L);
	when(this.interestService.updateInterest(interestDto)).thenReturn(savedInterestDto);
	RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/interests/update")
		.content(MasterData.asJsonString(interestDto)).contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON);

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	myAssert(currentTest(),
		(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedInterestDto))
			? "true"
			: "false"),
		businessTestFile);

    }

    @Test
     void testUpdateInterestIsServiceMethodCalled() throws Exception {
	final int count[] = new int[1];
	InterestDto interestDto = MasterData.getInterestDto();
	InterestDto savedInterestDto = MasterData.getInterestDto();
	interestDto.setId(1L);
	savedInterestDto.setId(1l);

	when(this.interestService.updateInterest(interestDto)).then(new Answer<InterestDto>() {

	    @Override
	    public InterestDto answer(InvocationOnMock invocation) throws Throwable {
		count[0]++;
		return savedInterestDto;
	    }
	});
	RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/interests/update")
		.content(MasterData.asJsonString(interestDto)).contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON);

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

}
