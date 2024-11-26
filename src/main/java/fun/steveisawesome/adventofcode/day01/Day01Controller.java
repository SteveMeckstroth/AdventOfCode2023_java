package fun.steveisawesome.adventofcode.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steveisawesome.adventofcode.Solution;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		String lines[] = inputData.split(System.lineSeparator());
		
		System.out.println("lines: \n" + Arrays.toString(lines));
		
		int total = 0;
		
		for (String calibration_value : lines) {
			String pattern = "\\d+";
			
			Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(calibration_value);
			
			List<String> allMatches = new ArrayList<String>();
			
			while(m.find()) {
				allMatches.add(m.group());
			}
			
			String firstNumber = allMatches.get(0).substring(0,1);
			String lastMatch = allMatches.get(allMatches.size() - 1);
			String lastNumber = lastMatch.substring(lastMatch.length()-1);
			
			int value = Integer.parseInt("%s%s".formatted(firstNumber, lastNumber));
			
			total += value;
			
            System.out.println(calibration_value + " First digit: " + firstNumber + " Last digit: " + lastNumber);
        }
		
		System.out.println("total: " + total);
	    
		return new Solution(1, 1, total);
	}
	
	

}