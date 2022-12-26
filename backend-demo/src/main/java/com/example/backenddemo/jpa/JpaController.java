package com.example.backenddemo.jpa;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos/")
public class JpaController {

    @Autowired
    TodoItemRepo todoItemRepo;

    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "201", description ="Item has been created" , content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{name}")
    public TodoItem createAndAddTodoItem(@PathVariable String name){
        TodoItem item = new TodoItem(name);
        todoItemRepo.save(item);  //i dont save them more in the list but now in the repository

        return  item;
    }

    @GetMapping("/count")
    public long getAmountofTodoItems(){
        return todoItemRepo.count();
    }

    @GetMapping("/id/{id}")  //will return me a specific todo_item
    public Optional<TodoItem> findbyId(@PathVariable String id){
        return todoItemRepo.findById(id);
    }

    @Operation(summary = "Returns a list of ToDo items")
    @GetMapping(value = "/", produces = "application/json")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200", description = "List all items" , content = @Content)
            })
    @ResponseStatus(HttpStatus.OK)
    public List<TodoItem> getTodoItems(){

        return todoItemRepo.findAll();
    }


    //deleting a specific todoItem
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(produces = "application/json", path = "/{itemId}")
    TodoItem deleteTodoItem(@PathVariable String itemId){

        todoItemRepo.deleteById(itemId);

        return null;
    }

}
