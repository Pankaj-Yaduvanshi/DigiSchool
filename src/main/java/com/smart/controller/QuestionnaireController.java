package com.smart.controller;
import com.smart.entities.Option;
import com.smart.entities.Questionnaire;
import com.smart.exceptions.ApiResponse;
import com.smart.repository.QuestionnaireRepository;
import com.smart.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;
    @PostMapping("/create")
    public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody Questionnaire questionnaire){
        Questionnaire savedQuestionnaire = questionnaireService.saveQuestionnaire(questionnaire);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestionnaire);
    }
    @DeleteMapping("/delete{qnId}")
    ResponseEntity<ApiResponse> deleteOption(@PathVariable Integer qnId){
        questionnaireService.deleteQuestionnaire(qnId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Questionnaire deleted successfully", true, HttpStatus.OK));
    }
}
