package com.example.javaspringtbootjpa.web;

import com.example.javaspringtbootjpa.business.TourRatingService;
import com.example.javaspringtbootjpa.model.RatingDto;
import com.example.javaspringtbootjpa.model.TourRating;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Tour Rating Controller */
@RestController
@RequestMapping(path = "/api/tours/{tourId}/ratings")
@Tag(name = "Rate the a tour and see the ratings between 1 to 5 with unique customer ID.")
public class TourRatingController {
  private TourRatingService tourRatingService;

  public TourRatingController(TourRatingService tourRatingService) {
    this.tourRatingService = tourRatingService;
  }

  /**
   * Create a Tour Rating.
   *
   * @param tourId
   * @param ratingDto
   */
  @PostMapping("/v1")
  @ResponseStatus(HttpStatus.CREATED)
  public RatingDto createTourRating(
      @PathVariable(value = "tourId") int tourId, @RequestBody @Valid RatingDto ratingDto) {
    TourRating rating =
        tourRatingService.createNew(
            tourId, ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment());
    return new RatingDto(rating);
  }

  @GetMapping
  public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId) {
    List<TourRating> tourRatings = tourRatingService.lookupRatings(tourId);
    return tourRatings.stream().map(RatingDto::new).toList();
  }

  /**
   * Calculate the average Score of a Tour.
   *
   * @param tourId
   * @return the average value.
   */
  @GetMapping("/average")
  public Map<String, Double> getAverage(@PathVariable(value = "tourId") int tourId) {
    return Map.of("average", tourRatingService.getAverageScore(tourId));
  }

  /**
   * Update score and comment of a Tour Rating
   *
   * @param tourId
   * @param ratingDto
   * @return The modified Rating DTO.
   */
  @PutMapping
  public RatingDto updateWithPut(
      @PathVariable(value = "tourId") int tourId, @RequestBody @Valid RatingDto ratingDto) {
    return new RatingDto(
        tourRatingService.update(
            tourId, ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment()));
  }

  /**
   * Delete a Rating of a tour made by a customer
   *
   * @param tourId
   * @param customerId
   */
  @DeleteMapping("/{customerId}")
  public void delete(
      @PathVariable(value = "tourId") int tourId,
      @PathVariable(value = "customerId") int customerId) {
    tourRatingService.delete(tourId, customerId);
  }

  /**
   * Create Several Tour Ratings for one tour, score and several customers.
   *
   * @param tourId tours ID
   * @param score bulk score for all
   * @param customers lost of customers ids
   */
  @PostMapping("/batch")
  @ResponseStatus(HttpStatus.CREATED)
  public void createManyTourRatings(
      @PathVariable(value = "tourId") int tourId,
      @RequestParam(value = "score") int score,
      @RequestBody List<Integer> customers) {
    tourRatingService.rateMany(tourId, score, customers);
  }
}