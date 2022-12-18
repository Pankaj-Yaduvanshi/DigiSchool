package com.smart.serviceImp;

import com.smart.entities.Question;
import com.smart.entities.Questionnaire;
import com.smart.exceptions.ResourceNotFoundException;
import com.smart.repository.QuestionnaireRepository;
import com.smart.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnareServiceImp implements QuestionnaireService {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private QuestionnaireService questionnaireService;

    @Override
    public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    public void deleteQuestionnaire(Integer qnId) {
        questionnaireRepository.deleteById(qnId);
    }

    @Override
    public Questionnaire getQuestionnaire(Integer qnId) {
        Questionnaire questionnaireFind = questionnaireRepository.findById(qnId).orElseThrow(()->new ResourceNotFoundException("Questionnaire Not Found"));
        return questionnaireFind;
    }
    @Override
    public List<Question> getQuestionByQuestionnaire(Integer qnId) {
        return null;
    }
}
