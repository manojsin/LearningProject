package com.grow.repository;
import com.grow.dao.TransactionStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionStatementRepository  extends JpaRepository<TransactionStatement,Long> {
}
