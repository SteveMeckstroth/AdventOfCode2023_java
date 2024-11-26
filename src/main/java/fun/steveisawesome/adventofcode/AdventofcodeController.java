package fun.steveisawesome.adventofcode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdventofcodeController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}