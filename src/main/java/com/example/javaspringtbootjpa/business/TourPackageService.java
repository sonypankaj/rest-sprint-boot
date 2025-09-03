package com.example.javaspringtbootjpa.business;

import com.example.javaspringtbootjpa.model.TourPackage;
import com.example.javaspringtbootjpa.repository.TourPackageRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TourPackageService {
  private final TourPackageRepository tourPackageRepository;

  public TourPackageService(TourPackageRepository tourPackageRepository) {
    this.tourPackageRepository = tourPackageRepository;
  }

  public TourPackage createTourPackage(String code, String name) {
    return tourPackageRepository
        .findById(code)
        .orElse(tourPackageRepository.save(new TourPackage(code, name)));
  }

  public List<TourPackage> lookupAll() {
    return tourPackageRepository.findAll();
  }

  public long total() {
    return tourPackageRepository.count();
  }
}
