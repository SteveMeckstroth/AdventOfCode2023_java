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
	public void part1() throws Exception {
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
	
	@Test
	public void part2() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/Day01/part2")
				.accept(MediaType.APPLICATION_JSON)
				.content("two1nine\n"
						+ "eightwothree\n"
						+ "abcone2threexyz\n"
						+ "xtwone3four\n"
						+ "4nineeightseven2\n"
						+ "zoneight234\n"
						+ "7pqrstsixteen")
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.answer").value(281))
				.andExpect(jsonPath("$.part").value(1))
				.andExpect(jsonPath("$.day").value(1));
	}
}