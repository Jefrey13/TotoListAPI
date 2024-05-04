package com.zuri.TodoApi.controller;

import com.zuri.TodoApi.repository.TodoRepository;
import com.zuri.TodoApi.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(value = "/")
    public String get(){
        return "Hello";
    }

    @GetMapping(value = "/getTasks")
    public ResponseEntity<?> getTasks() {
        try {
            List<Task> tasks = todoRepository.findAll();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving tasks: " + e.getMessage());
        }
    }

    @PostMapping(value = "/saveTask")
    public ResponseEntity<String> saveTask(@RequestBody Task task){
        try {
            todoRepository.save(task);
            return new ResponseEntity<>("Task Saved", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving task: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateTask/{id}")
    public ResponseEntity<String> updateTask(@PathVariable long id, @RequestBody Task task){
        try {
            Optional<Task> existingTask = todoRepository.findById(id);
            if (existingTask.isPresent()) {
                Task updatedTask = existingTask.get();
                updatedTask.setName(task.getName());
                updatedTask.setDescription(task.getDescription());
                todoRepository.save(updatedTask);
                return new ResponseEntity<>("Updated task", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Task not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating task: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value="/deleteTask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id){
        try {
            Optional<Task> taskToDelete = todoRepository.findById(id);
            if (taskToDelete.isPresent()) {
                todoRepository.delete(taskToDelete.get());
                return new ResponseEntity<>("Deleted task", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Task not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting task: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/changeState/{id}")
    public ResponseEntity<String> changeState(@PathVariable long id){
        try {
            Optional<Task> taskToChangeState = todoRepository.findById(id);
            if (taskToChangeState.isPresent()) {
                Task changedState = taskToChangeState.get();
                changedState.setState(true);
                todoRepository.save(changedState);
                return new ResponseEntity<>("Changed State", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Task not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error changing state: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
