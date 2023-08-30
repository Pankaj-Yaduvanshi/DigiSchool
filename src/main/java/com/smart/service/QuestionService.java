package com.smart.service;

//import com.smart.entities.Option;
import com.smart.entities.Question;

import java.util.List;

public interface QuestionService {
    Question saveQuestion (Question question);
    void deleteQuestion(Integer qId);
    Question getQuestion(Integer qId);
//    List<Option> getOptionByQuestion(Integer qId);
}
