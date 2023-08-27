package com.smart.controller;
import com.smart.entities.Question;
import com.smart.exceptions.ApiResponse;
import com.smart.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @PostMapping("/create")
    public ResponseEntity<Question> createQuestionnaire(@RequestBody Question question){
        Question savedQuestion = questionService.saveQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }
    @DeleteMapping("/delete{qId}")
    ResponseEntity<ApiResponse> deleteQuestion(@PathVariable Integer qId){
        questionService.deleteQuestion(qId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Question deleted successfully", true, HttpStatus.OK));
    }
}
