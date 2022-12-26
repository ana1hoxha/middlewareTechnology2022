package com.example.backenddemo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepo extends JpaRepository<TodoItem,String> {
}
