package com.project.artistwebsite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subject;
    private String title;
    private String body;
    private LocalDateTime dateTime;

}
