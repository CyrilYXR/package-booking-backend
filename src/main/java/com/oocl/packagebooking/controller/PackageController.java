package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping()
    public ResponseEntity findAll(){
        List<Package> packages = packageService.findAll();
        return ResponseEntity.ok().body(packages);
    }
}