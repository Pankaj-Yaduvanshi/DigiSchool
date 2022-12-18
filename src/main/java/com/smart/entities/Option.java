package com.smart.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int oId;
    private String description;
    @ManyToOne
    @JsonIgnore
    private Question question;

}