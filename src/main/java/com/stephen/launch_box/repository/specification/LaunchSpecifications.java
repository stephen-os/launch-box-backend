package com.stephen.launch_box.repository.specification;

import com.stephen.launch_box.model.Launch;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

public class LaunchSpecifications {
    public static Specification<Launch> withFilters(String companyName, String location, String startDate, String endDate,
                                                    Boolean rocketStatus, Boolean missionStatus, Float minCost, Float maxCost) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (companyName != null) {
                predicates.add(cb.equal(root.get("companyName"), companyName));
            }
            if (location != null) {
                predicates.add(cb.equal(root.get("location"), location));
            }
            if (startDate != null && endDate != null) {
                predicates.add(cb.between(root.get("date"),
                        LocalDateTime.parse(startDate), LocalDateTime.parse(endDate)));
            }
            if (rocketStatus != null) {
                predicates.add(cb.equal(root.get("rocketStatus"), rocketStatus));
            }
            if (missionStatus != null) {
                predicates.add(cb.equal(root.get("missionStatus"), missionStatus));
            }
            if (minCost != null && maxCost != null) {
                predicates.add(cb.between(root.get("cost"), minCost, maxCost));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
