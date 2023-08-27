package com.smart.service;

//import com.smart.entities.Option;
//import com.smart.entities.Question;
import com.smart.entities.Assignment;

public interface AssignmentService {
    Assignment saveQuestionnaire (Assignment questionnaire);
    void deleteQuestionnaire(Integer qnId);
    Assignment getQuestionnaire(Integer qnId);
//    List<Question> getQuestionByQuestionnaire(Integer qnId);
}
