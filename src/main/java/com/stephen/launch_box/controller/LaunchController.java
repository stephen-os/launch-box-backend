package com.stephen.launch_box.controller;

import com.stephen.launch_box.model.Launch;
import com.stephen.launch_box.service.LaunchService;
import com.stephen.launch_box.repository.specification.LaunchSpecifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/launches")
public class LaunchController {

    private final LaunchService launchService;

    @Autowired
    public LaunchController(LaunchService launchService) {
        this.launchService = launchService;
    }

    // Endpoint to get a launch by ID
    @GetMapping("/{id}")
    public ResponseEntity<Launch> getLaunchById(@PathVariable Long id) {
        Optional<Launch> launch = launchService.getLaunchById(id);

        if (launch.isPresent()) {
            return ResponseEntity.ok(launch.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Launch>> getAllLaunches() {
        List<Launch> launches = launchService.getAllLaunches();
        if (launches.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(launches);
    }

    // Search
    @GetMapping("/search")
    public ResponseEntity<Page<Launch>> searchLaunches(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Boolean rocketStatus,
            @RequestParam(required = false) Boolean missionStatus,
            @RequestParam(required = false) Float minCost,
            @RequestParam(required = false) Float maxCost,
            @RequestParam(defaultValue = "0") int page, // Default to the first page (0-indexed)
            @RequestParam(defaultValue = "100") int size // Default to 100 items per page
    ) {
        // Create a Specification based on the provided filter parameters
        Specification<Launch> spec = LaunchSpecifications.withFilters(
                companyName, location, startDate, endDate, rocketStatus, missionStatus, minCost, maxCost
        );

        // Create Pageable object for pagination
        Pageable pageable = PageRequest.of(page, size);

        // Get the results using the specification and pageable
        Page<Launch> results = launchService.searchLaunches(spec, pageable);

        // Return the results as a Page object, including metadata for pagination
        return ResponseEntity.ok(results);
    }

}
