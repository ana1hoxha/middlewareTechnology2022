package com.example.hfttodo;

import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/todos")
public class TodoController {

 private ArrayList<Todo> items = new ArrayList<Todo>();


    @Operation(summary = "Creates a Todo Item with path variable name and default priority of 2")
    @ApiResponses(value = 
    { @ApiResponse(responseCode = "201", description = "Item has been created" , content = @Content) })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{name}")
    public Todo createAndAddTodoItem(@PathVariable String name){
        Todo item= new Todo(name);
        items.add(item);
        return item; }


    @Operation(summary="Creates a Todo Item with a JSON object as request parameter")
    @ApiResponses(value= {@ApiResponse(responseCode="201", description= "Item has been created", content=@Content)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Todo addTodoItem(@RequestBody Todo item){
        items.add(item);
        return item;
    }  
    
    

    @Operation(summary="Returns a list of Todo items")
    @ApiResponses( value =
        {@ApiResponse(responseCode="200", description= "List of all items", content=@Content)})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value ="/", produces = "application/json")
    public List<Todo> getTodoItems(){
        return items;
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(produces= "application/json", path = "/{itemId}")
    Todo deleteTodoItem(@PathVariable String itemId){
        Todo tempItem = new Todo(itemId);
        Iterator<Todo> iterator = items.iterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(tempItem))
                 iterator.remove(); }
        return null;
    }






}