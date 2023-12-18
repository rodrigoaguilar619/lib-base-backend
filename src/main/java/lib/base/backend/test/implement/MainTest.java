package lib.base.backend.test.implement;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MainTest {

	protected MockMvc mockMvc;
	
	public <T> void buildMock(T instance) {
		mockMvc = MockMvcBuilders.standaloneSetup(instance).build();
	}
}
