package com.smart.service;

import com.smart.entities.Option;
import com.smart.entities.Question;
import com.smart.entities.Questionnaire;

import java.util.List;

public interface QuestionnaireService {
    Questionnaire saveQuestionnaire (Questionnaire questionnaire);
    void deleteQuestionnaire(Integer qnId);
    Questionnaire getQuestionnaire(Integer qnId);
    List<Question> getQuestionByQuestionnaire(Integer qnId);
}
