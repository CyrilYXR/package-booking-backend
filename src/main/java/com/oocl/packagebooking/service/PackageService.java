package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public List<Package> findAll() {
        return packageRepository.findAll();
    }

    public Package add(Package p) {
        return packageRepository.save(p);
    }

    public List<Package> findByStatus(Integer status) {
        return packageRepository.findByStatus(status);
    }

    public Package update(Long id, Package p) {
        Package newPackage = packageRepository.findById(id).get();
        if(p.getName()!=null){
            newPackage.setName(p.getName());
        }
        if(p.getPhone()!=null){
            newPackage.setPhone(p.getPhone());
        }
        if(p.getReserveTime()!=null){
            newPackage.setReserveTime(p.getReserveTime());
        }
        if(p.getStatus()!=null){
            newPackage.setStatus(p.getStatus());
        }
        if(p.getWeight()!=null){
            newPackage.setWeight(p.getWeight());
        }
        return  packageRepository.save(newPackage);
    }
}
