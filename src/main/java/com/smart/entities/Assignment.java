package com.smart.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="Assignment")
public class Assignment {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer aId;
        private String sopTitle;
        private String sopNumber;
        private String questionnaireNumber;
        private String questionnaireVersion;
        @OneToMany(mappedBy = "assignment",cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Question> questions = new ArrayList<>();

}
