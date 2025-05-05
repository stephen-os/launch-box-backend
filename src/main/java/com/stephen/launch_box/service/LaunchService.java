package com.stephen.launch_box.service;

import com.stephen.launch_box.model.Launch;
import com.stephen.launch_box.repository.LaunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

@Service
public class LaunchService {

    private final LaunchRepository launchRepository;

    @Autowired
    public LaunchService(LaunchRepository launchRepository) {
        this.launchRepository = launchRepository;
    }

    // Method to fetch all launches
    public List<Launch> getAllLaunches() {
        return launchRepository.findAll();
    }

    public Optional<Launch> getLaunchById(Long id) {
        return launchRepository.findById(id);
    }

    public Page<Launch> searchLaunches(Specification<Launch> spec, Pageable pageable) {
        return launchRepository.findAll(spec, pageable);
    }
}
