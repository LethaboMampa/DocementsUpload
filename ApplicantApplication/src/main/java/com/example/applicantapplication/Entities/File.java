package com.example.applicantapplication.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Files")
public class File{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] data;

    private String fileName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // This should be the foreign key column name
    private User user;
}
