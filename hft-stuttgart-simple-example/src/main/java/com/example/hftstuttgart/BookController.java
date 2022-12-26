package com.example.hftstuttgart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List<Book> findbyTitle(@PathVariable String bookTitle){
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public Optional<Book> findOne(@PathVariable Long id){
        return bookRepository.findById(id);}



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        bookRepository.deleteById(id);
    }

    //@RequestParams extract values from the query string (encoded), @PathVariables (not encoded) extract values from the URI path
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id){
        return bookRepository.save(book);}




}
