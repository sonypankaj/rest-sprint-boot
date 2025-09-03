package com.example.javaspringtbootjpa.repository;

import com.example.javaspringtbootjpa.model.Difficulty;
import com.example.javaspringtbootjpa.model.Tour;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Integer> {
  List<Tour> findByDifficulty(Difficulty difficulty);
  List<Tour> findByTourPackageCode(String tourPackageCode);
}
