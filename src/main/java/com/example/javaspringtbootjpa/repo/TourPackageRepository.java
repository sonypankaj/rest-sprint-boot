package com.example.javaspringtbootjpa.repo;

import com.example.javaspringtbootjpa.model.TourPackage;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "packages", collectionResourceRel = "packages")
public interface TourPackageRepository extends JpaRepository<TourPackage, String> {

  Optional<TourPackage> findByName(String name);
}
