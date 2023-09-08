package org.crustproject.taskmanagement.data.repository;

import org.crustproject.taskmanagement.data.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
