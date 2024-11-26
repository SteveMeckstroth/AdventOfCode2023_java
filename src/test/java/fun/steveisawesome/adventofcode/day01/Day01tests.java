package fun.steveisawesome.adventofcode.day01;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class Day01tests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/Day01/part1")
				.accept(MediaType.APPLICATION_JSON)
				.content("1abc2\n"
						+ "pqr3stu8vwx\n"
						+ "a1b2c3d4e5f\n"
						+ "treb7uchet")
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.answer").value(142))
				.andExpect(jsonPath("$.part").value(1))
				.andExpect(jsonPath("$.day").value(1));
	}
}