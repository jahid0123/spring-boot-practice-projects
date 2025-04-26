package com.jmjbrothers.student_crud.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_table")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private  String name;
    private int roll;
    private String contact;
    private String address;

/*    public Student(String name, int roll, String contact, String address) {
        this.name = name;
        this.roll = roll;
        this.contact = contact;
        this.address = address;
    }*/
}
