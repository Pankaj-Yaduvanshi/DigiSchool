package com.DigiSchool.Repository;

import com.DigiSchool.Model.Question;
import com.DigiSchool.Model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}
