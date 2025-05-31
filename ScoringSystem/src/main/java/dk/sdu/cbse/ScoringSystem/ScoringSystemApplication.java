package dk.sdu.cbse.ScoringSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ScoringSystemApplication {
	private int score = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoringSystemApplication.class, args);
	}

	@PutMapping("/score/add/{score}")
	public int incrementScore(@PathVariable(value = "score") int score) {
		this.score += score;
		return this.score;
	}

	@GetMapping("/score/get")
	public int getScore() {
		return this.score ;
	}

	@PutMapping("/score/set/{score}")
	public int resetScore(@PathVariable(value = "score") int score) {
		this.score = score;
		return this.score;
	}
}
