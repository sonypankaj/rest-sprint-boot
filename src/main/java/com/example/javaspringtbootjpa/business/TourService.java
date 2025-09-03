package com.example.javaspringtbootjpa.business;

import com.example.javaspringtbootjpa.model.Difficulty;
import com.example.javaspringtbootjpa.model.Region;
import com.example.javaspringtbootjpa.model.Tour;
import com.example.javaspringtbootjpa.model.TourPackage;
import com.example.javaspringtbootjpa.repository.TourPackageRepository;
import com.example.javaspringtbootjpa.repository.TourRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TourService {
  private TourPackageRepository tourPackageRepository;
  private TourRepository tourRepository;

  public TourService(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
    this.tourPackageRepository = tourPackageRepository;
    this.tourRepository = tourRepository;
  }

  public Tour createTour(
      String tourPackageName,
      String title,
      String description,
      String blurb,
      Integer price,
      String duration,
      String bullets,
      String keywords,
      Difficulty difficulty,
      Region region) {

    TourPackage tourPackage =
        tourPackageRepository
            .findByName(tourPackageName)
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Tour package does not exist with ID: " + tourPackageName));

    return tourRepository.save(
        new Tour(
            title,
            description,
            blurb,
            price,
            duration,
            bullets,
            keywords,
            tourPackage,
            difficulty,
            region));
  }

  public long total() {
    return tourRepository.count();
  }

  public List<Tour> lookupByDifficulty(Difficulty difficulty) {
    return tourRepository.findByDifficulty(difficulty);
  }

  public List<Tour> lookupByPackageCode(String tourPackageCode) {
    return tourRepository.findByTourPackageCode(tourPackageCode);
  }
}
