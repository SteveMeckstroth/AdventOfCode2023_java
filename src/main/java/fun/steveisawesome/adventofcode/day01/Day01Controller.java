package fun.steveisawesome.adventofcode.day01;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steveisawesome.adventofcode.Solution;

@RestController
@RequestMapping("/api/Day01")
public class Day01Controller {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@PostMapping("/part1")
	public Solution part1(@RequestBody String inputData) {
		System.out.println("Received inputData: " + inputData);
	    
		return new Solution(1, 1, 123123412);
	}

}