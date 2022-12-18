package com.smart.repository;

import com.smart.entities.Option;
import com.smart.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Integer> {
}
