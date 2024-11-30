package fun.steveisawesome.adventofcode.day02;

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
public class Day02tests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void part1() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/Day02/part1")
				.accept(MediaType.APPLICATION_JSON)
				.content("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n"
						+ "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n"
						+ "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n"
						+ "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n"
						+ "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.answer").value(8))
				.andExpect(jsonPath("$.part").value(1))
				.andExpect(jsonPath("$.day").value(1));
	}
	
	@Test
	public void part2() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/Day02/part2")
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