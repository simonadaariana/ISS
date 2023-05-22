package com.example.proiect.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "bug", schema = "public")
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String testerName;
    private String bugName;
    private String description;
    private Boolean solved;

    public Bug(String testerName, String bugName, String description, Boolean solved) {
        this.testerName = testerName;
        this.bugName = bugName;
        this.description = description;
        this.solved = solved;
    }
}

