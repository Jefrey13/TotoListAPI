package com.zuri.TodoApi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zuri.TodoApi.Model.Task;
public interface TodoRepository extends JpaRepository<Task, Long> {
}
