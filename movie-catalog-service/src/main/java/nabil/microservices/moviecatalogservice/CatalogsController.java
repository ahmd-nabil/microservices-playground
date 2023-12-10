package nabil.microservices.moviecatalogservice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Ahmed Nabil
 */

@RestController
@RequestMapping("/catalogs")
public class CatalogsController {

    private final RestTemplate restTemplate;

    public CatalogsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<List<Catalog>> getAllCatalogsForMovie(@PathVariable(name = "movieId") Integer movieId) {
        // using var messes up generics (return type of getForObject becomes array for no reason)
        Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + movieId, Movie.class);
        // fetch all ratings for that movie and for each rating create a catalog (bad idea, but we're playing around :D )
        List<Catalog> ratings = restTemplate
                .exchange("http://localhost:8082/ratings/" + movieId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Catalog>>(){}).getBody();
        List<Catalog> catalogs = ratings.stream().map(rating -> new Catalog(movie.name, rating.rating, rating.ratingDesc)).toList();
        return ResponseEntity.ok(catalogs);
    }

    record Catalog(String movieName, Integer rating, String ratingDesc) {}
    record Rating (Integer userId, Integer movieId,Integer rating, String ratingDesc) {}
    record Movie (Integer id, String name) {}
}
