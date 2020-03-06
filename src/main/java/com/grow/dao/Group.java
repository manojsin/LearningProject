package com.grow.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Group")
@Data
public class Group {
    @Id
    private int id;
    @Column
    private int groupId;
    @Column
    private String  groupName;
    @Column
    private double totalAmount;
    @Column
    private int numOfUser;
}
