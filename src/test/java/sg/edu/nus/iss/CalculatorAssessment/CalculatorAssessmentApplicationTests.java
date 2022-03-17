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
import jakarta.json.JsonReader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorAssessmentApplicationTests {

	@Autowired
	CalculatorController calControl;

	@Autowired
	MockMvc mvc;

	@Test
	void contextLoads(){

		assertNotNull(calControl);
	}


	@Test
	void shoudReturnCorrectResult() throws Exception{

		JsonObject body = Json.createObjectBuilder()
							.add("oper1", 1)
							.add("oper2", 2)
							.add("ops", "plus").build();

		RequestBuilder req = MockMvcRequestBuilders.post("/calculate")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(body.toString())
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.header("User-Agent", "junit");

		MvcResult resp = mvc.perform(req).andReturn();
		MockHttpServletResponse httpResp = resp.getResponse();

		assertEquals(200, httpResp.getStatus());
		
		Optional<JsonObject> opt = string2Json(httpResp.getContentAsString());
		assertTrue(opt.isPresent());

		JsonObject obj = opt.get();

		for(String s: List.of("result","timestamp","userAgent")){
			assertFalse(obj.isNull(s));
		}

		assertEquals(3, obj.getInt("result"));

	}

	public static Optional<JsonObject> string2Json(String s){
		try (InputStream is = new ByteArrayInputStream(s.getBytes())) {
			JsonReader reader = Json.createReader(is);
			return Optional.of(reader.readObject());
		} catch (Exception ex) {
			System.err.printf("Error: %s\n", ex.getMessage());
			return Optional.empty();
	}

	}
}
