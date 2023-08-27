package com.smart.controller;
//import com.smart.entities.Option;
import com.smart.entities.Assignment;
import com.smart.exceptions.ApiResponse;
import com.smart.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questionnaire")
public class AssignmentController {
    @Autowired
    private AssignmentService questionnaireService;
    @PostMapping("/create")
    public ResponseEntity<Assignment> createQuestionnaire(@RequestBody Assignment questionnaire){
        Assignment savedQuestionnaire = questionnaireService.saveQuestionnaire(questionnaire);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestionnaire);
    }
    @DeleteMapping("/delete{qnId}")
    ResponseEntity<ApiResponse> deleteOption(@PathVariable Integer qnId){
        questionnaireService.deleteQuestionnaire(qnId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Questionnaire deleted successfully", true, HttpStatus.OK));
    }
}
