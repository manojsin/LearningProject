package com.grow.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TransactionStatement")
@Data
public class TransactionStatement {
    @Id
    private int groupId;
    @Column
    private String groupName;
    @Column
    private String transactionDate;
    @Column
    private String transactionId;
}
