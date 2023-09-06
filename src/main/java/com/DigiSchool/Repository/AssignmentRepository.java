package com.DigiSchool.Repository;

import com.DigiSchool.Model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
}
