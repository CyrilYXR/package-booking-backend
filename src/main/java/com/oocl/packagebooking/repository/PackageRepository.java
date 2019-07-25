package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {
}
