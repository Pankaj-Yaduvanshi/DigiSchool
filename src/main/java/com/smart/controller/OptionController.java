package com.smart.controller;

import com.smart.entities.Option;
import com.smart.exceptions.ApiResponse;
import com.smart.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/option")
public class OptionController {
    @Autowired
    private OptionService optionService;
    @PostMapping("/create")
    public ResponseEntity<Option> createOption(@RequestBody Option option){
        Option savedOption = optionService.saveOption(option);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOption);
    }
    @DeleteMapping("/delete{oId}")
    ResponseEntity<ApiResponse> deleteOption(@PathVariable Integer oId){
        optionService.deleteOption(oId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Option deleted successfully", true, HttpStatus.OK));
    }
}
