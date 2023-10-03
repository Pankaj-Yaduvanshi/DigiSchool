package com.DigiSchool.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Person" )
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name field is required !!")
	@Size(min = 2,max = 20,message = "min 2 and max 20 characters are allowed !!")
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	private String imageUrl;
	@Column(length = 500)
	private String about;
}
