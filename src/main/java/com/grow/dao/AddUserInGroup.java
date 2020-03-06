package com.grow.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="AddUserInGroup")
@Data
public class AddUserInGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int groupId;
    @Column
    private String groupName;
}
