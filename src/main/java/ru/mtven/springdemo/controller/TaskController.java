package ru.mtven.springdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mtven.springdemo.model.Task;
import ru.mtven.springdemo.repository.TaskRepository;
import ru.mtven.springdemo.service.UserService;


@RestController
@Slf4j
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/tasks")
    public Iterable<Task> getAll() {
        Long userId = userService.getCurrentUser().getId();
        log.info("Current user id = " + userId);
        return taskRepository.findAllByUserId(userId);
    }

    @GetMapping("/tasks/{id}")
    public Task get(@PathVariable Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}")
    public Task update(@PathVariable Long id,
                       @RequestBody Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @PatchMapping("/tasks/{id}")
    public void markAsDone(@PathVariable Long id) {
        taskRepository.markAsDone(id);
    }

    @DeleteMapping("tasks/{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
