package com.DigiSchool.Repository;

import com.DigiSchool.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
     }
