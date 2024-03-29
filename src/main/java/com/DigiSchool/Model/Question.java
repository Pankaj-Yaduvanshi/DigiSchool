package com.DigiSchool.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int qId;
    private String description;
    private String answer;
    private String[] options;
    @ManyToOne
    private Assignment assignment;
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return this.qId==((Question)obj).getQId();
    }

}