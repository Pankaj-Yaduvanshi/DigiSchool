package com.smart.entities;
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
@Table(name="Options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int oId;
    private String description;
    @ManyToOne
    private Question question;

}