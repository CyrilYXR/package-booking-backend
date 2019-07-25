package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity add(@RequestBody Package p){
        Package aPackage = packageService.add(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(aPackage);
    }

    @GetMapping(params = {"status"})
    public ResponseEntity findByStatus(@RequestParam Integer status){
        List<Package> byStatus = packageService.findByStatus(status);
        return ResponseEntity.ok().body(byStatus);
    }
}
