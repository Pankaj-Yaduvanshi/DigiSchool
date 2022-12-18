package com.smart.repository;

import com.smart.entities.Question;
import com.smart.entities.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
}
