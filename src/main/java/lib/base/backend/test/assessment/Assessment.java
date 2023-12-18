package lib.base.backend.test.assessment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Assessment {
	
	
	public Assessment() {
		super();
	}

	public static <T> void assertResponseAndDataList(MockMvc mockMvc, T requestPojo, List<?> dataPojo, String uri) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(requestPojo);
		
        mockMvc.perform(post(uri)
		.contentType(MediaType.APPLICATION_JSON)
		.content(requestJson)
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
        assertNotNull(dataPojo);
        assertThat(dataPojo.size(), greaterThan(0));
	}
	
	public static <T> void assertDataList(List<?> dataListPojo) throws Exception {
		
        assertNotNull(dataListPojo);
        assertThat(dataListPojo.size(), greaterThan(0));
	}
}
