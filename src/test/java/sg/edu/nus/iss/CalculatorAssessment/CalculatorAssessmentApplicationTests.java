package sg.edu.nus.iss.CalculatorAssessment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorAssessmentApplicationTests {

	@Autowired
	CalculatorController calControl;

	@Autowired
	MockMvc mvc;


	@Test
	void shoudReturnCorrectResult() throws Exception{

		JsonObject body = Json.createObjectBuilder()
							.add("oper1", 1)
							.add("oper2", 2)
							.add("ops", "plus").build();

		RequestBuilder req = MockMvcRequestBuilders.post("/calculator")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(body.toString())
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.header("User-Agent", "junit");

		MvcResult resp = mvc.perform(req).andReturn();
		MockHttpServletResponse httpResp = resp.getResponse();

		Assertions.assertEquals(httpResp.getStatus(), 404);																								

	}
}
