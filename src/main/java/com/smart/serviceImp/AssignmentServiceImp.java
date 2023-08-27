package com.smart.serviceImp;

//import com.smart.entities.Question;
import com.smart.entities.Assignment;
import com.smart.exceptions.ResourceNotFoundException;
import com.smart.repository.AssignmentRepository;
import com.smart.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImp implements AssignmentService {
    @Autowired
    private AssignmentRepository questionnaireRepository;
    @Autowired
    private AssignmentService questionnaireService;

    @Override
    public Assignment saveQuestionnaire(Assignment questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    public void deleteQuestionnaire(Integer qnId) {
        questionnaireRepository.deleteById(qnId);
    }

    @Override
    public Assignment getQuestionnaire(Integer qnId) {
        Assignment questionnaireFind = questionnaireRepository.findById(qnId).orElseThrow(()->new ResourceNotFoundException("Questionnaire Not Found"));
        return questionnaireFind;
    }
//    @Override
//    public List<Question> getQuestionByQuestionnaire(Integer qnId) {
//        return null;
//    }
}
