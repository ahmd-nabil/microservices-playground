package nabil.microservices.movieratingservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ahmed Nabil
 */
@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @GetMapping("/{movieId}")
    public ResponseEntity<List<Rating>> getRatings(@PathVariable(name = "movieId") Integer movieId) {
        return ResponseEntity.ok(
                List.of(new Rating(1, 1, 4, "Amazing movie!!!"),
                        new Rating(1, 1, 1, "Bad movie!!!"),
                        new Rating(1, 1, 5, "Amazing movie!!!")));
    }

    record Rating (Integer userId, Integer movieId,Integer rating, String ratingDesc) {}

}
