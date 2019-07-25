package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findByStatus(Integer status);
}
