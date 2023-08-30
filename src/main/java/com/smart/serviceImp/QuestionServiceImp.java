package com.smart.serviceImp;

//import com.smart.entities.Option;
import com.smart.entities.Question;
import com.smart.exceptions.ResourceNotFoundException;
import com.smart.repository.QuestionRepository;
import com.smart.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
    @Override
    public void deleteQuestion(Integer qId) {
        questionRepository.deleteById(qId);
    }
    @Override
    public Question getQuestion(Integer qId) {
        return questionRepository.findById(qId).orElseThrow(()-> new ResourceNotFoundException("Question Not found"));
    }

//    @Override
//    public List<Option> getOptionByQuestion(Integer qId) {
//        return null;
//    }
}
