package com.DigiSchool.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.persistence.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rId;
    @Column(columnDefinition = "TEXT")
    private String result; // Store JSON data as a string
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "assignment_assignment_id")
    private Assignment assignment;
}

