package com.smart.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Questioners")
public class Questionnaire {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int qnId;
        private String governingProcedureNumber;
        private String governingProcedureTitle;
        private String QuestionnaireFormNumber;

        @OneToMany(mappedBy = "questionnaire",cascade = CascadeType.ALL,orphanRemoval = true)
        private List<Question> questions=new ArrayList<>();
}
