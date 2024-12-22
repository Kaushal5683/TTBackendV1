package com.example.timetable.controller;

import com.example.timetable.entity.Year;
import com.example.timetable.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/years")
public class YearController {

    @Autowired
    private YearService yearService;

    @GetMapping
    public List<Year> getAllYears() {
        return yearService.getAllYears();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Year> getYearById(@PathVariable Long id) {
        return yearService.getYearById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Year createYear(@RequestBody Year year) {
        return yearService.createYear(year);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Year> updateYear(@PathVariable Long id, @RequestBody Year year) {
        return yearService.getYearById(id)
                .map(existingYear -> {
                    year.setId(id);
                    return ResponseEntity.ok(yearService.createYear(year));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYear(@PathVariable Long id) {
        return yearService.getYearById(id)
                .map(existingYear -> {
                    yearService.deleteYear(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
