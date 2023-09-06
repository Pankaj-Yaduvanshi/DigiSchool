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
@Table(name="Assignment")
public class Assignment {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer assignmentId;
        private String sopTitle;
        private String sopNumber;
        private String questionnaireNumber;
        private String questionnaireVersion;
//        @OneToMany(mappedBy = "assignment",cascade = CascadeType.ALL, orphanRemoval = true)
        @Transient
        private List<Question> questions;

}
