package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Images;

@Repository
public interface ImageRepository extends JpaRepository<Images, Integer> {

}
