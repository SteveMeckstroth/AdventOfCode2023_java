package fun.steveisawesome.adventofcode.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fun.steveisawesome.adventofcode.Solution;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;

@RestController
@RequestMapping("/api/Day02")
public class Day02Controller {
	
	@PostMapping("/part1")
	public Solution part1(@RequestBody String inputData) throws JsonMappingException, JsonProcessingException {
		
		String lines[] = inputData.split(System.lineSeparator());
		
		
		int total = 0;
		Map<Integer,Boolean> possibleGames = Collections.emptyMap();
		
		for (String game : lines) {
			int gameNumber = getGameNumber(game);
			String allResults = game.substring(game.indexOf(":") + 1);
			Boolean possible = true;
			for (String hand : allResults.split(";")) {
				for (String cube : hand.split(",")) {
					cube = cube.trim();
					int count = Integer.parseInt(cube.split(" ")[0]);
					String color = cube.split(" ")[1];
					System.out.println("Game: " + gameNumber + " hand: " + hand + " cube: " + cube + " color: " + color + " count: " + count);
					
					String maxCubesJson = """
							{
			                "red": 12,
			                "green": 13,
			                "blue": 14
			            }
			            """;
					
					Map<String, Integer> maxCubes =
				        new ObjectMapper().readValue(maxCubesJson, HashMap.class);
					
					if (count > maxCubes.get(color)) {
						possible = false;
						break;
					}
				}
			}
			if (possible == true) {
				total += gameNumber;
			}
		}
		
		System.out.println("total: " + total);
	    
		return new Solution(1, 1, total);
	}
	
	int getGameNumber(String line) {
		
		String pattern = "Game\\s(\\d+)[:]";
		
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(line);
		
		List<String> allMatches = new ArrayList<String>();
		int gameNumber = 0;
		
		if (m.find()) {
			gameNumber = Integer.parseInt(m.group(1));
		}
		
		System.out.println("Game Number: "+ gameNumber);
		
		return gameNumber;
			
        
	}
	
	@PostMapping("/part2")
	public Solution part2(@RequestBody String inputData) throws JsonMappingException, JsonProcessingException {
		System.out.println("Received inputData: " + inputData);
		
		String lines[] = inputData.split(System.lineSeparator());
		
		System.out.println("lines: \n" + Arrays.toString(lines));
		
		int total = 0;
		
		for (String line : lines) {
			
			String calibration_value = wordsToNumbers(line);
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
			
            System.out.println("line: " + line + " calibration value " + calibration_value + " First digit: " + firstNumber + " Last digit: " + lastNumber);
        }
		
		System.out.println("total: " + total);
	    
		return new Solution(1, 1, total);
	}
	
	String wordsToNumbers(String line) throws JsonMappingException, JsonProcessingException {
		String wordMapJSON = """
				{
                "one": 1,
                "two": 2,
                "three": 3,
                "four": 4,
                "five": 5,
                "six": 6,
                "seven": 7,
                "eight": 8,
                "nine": 9

            }
            """;
		
		Map<String, Integer> wordMap =
	        new ObjectMapper().readValue(wordMapJSON, HashMap.class);
		
		for (int i = 1; i < 10; i++) {
			wordMap.put(String.valueOf(i), i);
		}
		Map<Integer, String> indexes = new HashMap<>();
		
		for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            
            int index = line.indexOf(key);
            if (index != -1) {
            	indexes.put(index, key);
            }
            
            int lastIndex = line.lastIndexOf(key);
            if (lastIndex != -1) {
            	indexes.put(lastIndex, key);
            }
        
		}
        
		Map.Entry<Integer, String> min = Collections.min(indexes.entrySet(),
                Map.Entry.comparingByKey());
        
        Map.Entry<Integer, String> max = Collections.max(indexes.entrySet(),
                Map.Entry.comparingByKey());
        
        String newLine = line.replaceFirst(min.getValue(), wordMap.get(min.getValue()).toString());
        
        
        StringBuilder strb=new StringBuilder(newLine);    
        int lastIndex=strb.lastIndexOf(max.getValue());    
        strb.replace(lastIndex, max.getValue().length() + lastIndex, wordMap.get(max.getValue()).toString());
        System.out.println(indexes.toString());
        System.out.println("Max value: " + max.getValue() + " wordMap: " + wordMap.get(max.getValue()).toString());
        return strb.toString();    
            
        
	}
	

}