package com.stephen.launch_box.loader;

import com.stephen.launch_box.model.Launch;
import com.stephen.launch_box.repository.LaunchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LaunchLoader {

    private static final Logger logger = LoggerFactory.getLogger(LaunchLoader.class);

    @Bean
    public CommandLineRunner loadData(LaunchRepository launchRepository) {
        return args -> {
            List<Launch> launches = loadLaunchData("data/launches.csv");  // Provide CSV path here
            launchRepository.saveAll(launches);
            System.out.println("Data loaded successfully!");
        };
    }

    private List<Launch> loadLaunchData(String resourcePath) {
        List<Launch> launches = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource(resourcePath);
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)
            ) {
                for (CSVRecord record : csvParser) {
                    Launch launch = new Launch();
                    launch.setId(Long.parseLong(record.get(0)));
                    launch.setCompanyName(record.get(1));
                    launch.setLocation(record.get(2));
                    launch.setDate(LocalDateTime.parse(record.get(3), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    launch.setDetail(record.get(4));
                    launch.setRocketStatus(Boolean.parseBoolean(record.get(5)));
                    launch.setCost(record.get(6).isBlank() ? null : Float.parseFloat(record.get(6)));
                    launch.setMissionStatus(Boolean.parseBoolean(record.get(7)));

                    logger.info("Importing Launch: {}", launch);
                    launches.add(launch);
                }
            }
        } catch (IOException e) {
            logger.error("Failed to load CSV", e);
        }
        return launches;
    }
}