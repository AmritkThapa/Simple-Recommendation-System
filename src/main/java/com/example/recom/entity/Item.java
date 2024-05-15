package com.example.recom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "items")
public class Item {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "category", length = 50)
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
