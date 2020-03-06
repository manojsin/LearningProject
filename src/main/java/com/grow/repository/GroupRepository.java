package com.grow.repository;

import com.grow.dao.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

    Group findByGroupId(int groupId);

    Group findByGroupName(String groupName);
}
