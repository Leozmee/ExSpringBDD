package org.example.ex2tdd.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personne")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "La valeur ne doit pas être vide !")
    @Size(min = 3, message = "Minimum 3 lettres svp !")
    @Column(name = "first_name")
    private String firstname;
    @NotBlank(message = "La valeur ne doit pas être vide !")
    @Column(name = "last_name")
    private String lastname;
    @NotNull(message = "Ce champ doit être rempli !")
    @Min(value = 18)
    @Max(103)
    @Column(name = "age")
    private int age;
    @NotBlank(message = "La valeur ne doit pas être vide !")
    @Pattern(regexp = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", message = "Format de l'email invalide !")
    @Column(name = "email")
    private String email;
    private String picture;
}