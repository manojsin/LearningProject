package com.grow.repository;

import com.grow.dao.AddUserInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddUserInGroupRepository extends JpaRepository<AddUserInGroup,Long> {
}
