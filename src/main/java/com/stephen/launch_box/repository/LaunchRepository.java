package com.stephen.launch_box.repository;

import com.stephen.launch_box.model.Launch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LaunchRepository extends JpaRepository<Launch, Long>, JpaSpecificationExecutor<Launch> {
}
