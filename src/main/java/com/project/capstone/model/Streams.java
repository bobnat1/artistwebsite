package com.project.capstone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

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
