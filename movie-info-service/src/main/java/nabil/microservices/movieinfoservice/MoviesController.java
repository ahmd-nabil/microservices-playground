package nabil.microservices.movieinfoservice;

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
@RequestMapping("/movies")
public class MoviesController {
    @GetMapping("")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(List.of(
                new Movie(1, "John Wick 4"),
                new Movie(2, "John Wick 3"),
                new Movie(3, "John Wick 2")
                ));
    }


    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("movieId") Integer movieId) {
        return ResponseEntity.ok(new Movie(movieId, "John Wick 4"));
    }
    record Movie (Integer id, String name) {}
}
