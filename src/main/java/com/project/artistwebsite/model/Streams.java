package com.project.artistwebsite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Streams {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String htmlCode;
    private String name;


}
