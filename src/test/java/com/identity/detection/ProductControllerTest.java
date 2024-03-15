package com.identity.detection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

/*
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest
 * 
 * @AutoConfigureMockMvc public class ProductControllerTest {
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @Test public void returnsHelloWorld() throws Exception { mockMvc
 * .perform(get("/ping")) .andExpect(status().isOk()); } }
 */

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void checksPing() throws Exception {
		mockMvc.perform(get("/ping")).andExpect(status().isOk());
	}

	@Test
	public void testvoiceanalyze() throws Exception {

		// mockMvc.perform(post("/voice/analyze")).andExpect(status().isOk());

		MockMultipartFile multipartFile = new MockMultipartFile("file", "human_voice_Script2.mp3", "text/plain",
				"Spring Framework".getBytes());
		this.mockMvc.perform(multipart("/voice/analyze").file(multipartFile)).andExpect(status().isOk())
				;

	}
}
