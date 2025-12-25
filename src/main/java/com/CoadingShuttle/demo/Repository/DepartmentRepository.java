package com.CoadingShuttle.demo.Repository;

import com.CoadingShuttle.demo.Dto.DepartmentDto;
import com.CoadingShuttle.demo.entitites.DepartmentEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntities,Long> {
}
