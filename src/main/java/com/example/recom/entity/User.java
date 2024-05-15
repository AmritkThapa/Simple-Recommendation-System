package com.example.recom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "name", length = 50)
    private String name;
}
