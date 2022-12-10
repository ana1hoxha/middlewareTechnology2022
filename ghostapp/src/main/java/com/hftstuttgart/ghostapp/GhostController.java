package com.hftstuttgart.ghostapp;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spooky")
public class GhostController {

    Map<String, Ghost> ghosts = new HashMap<>();

    @GetMapping("/littlespook")
    public String littleSpook() {
        return "...Boo!!";
    }

    @PostMapping("/ghost")
    @ResponseStatus(HttpStatus.CREATED)
    public Ghost create(@RequestBody Ghost ghost){
    ghosts.put(ghost.getName(), ghost);
    return ghost;}

    @GetMapping("/ghosts")
    public List<Ghost> getAllGhosts(){
        return ghosts.values().stream().toList();
    }

  /*  @PutMapping("/ghost")
    public Ghost setGhostName(@RequestBody Ghost ghost){
        Ghost oldghost = ghosts.get(ghost.getName());
        ghosts.put(ghost.getName(),ghost);
        return ghost;
    }
    */

    @PutMapping("/setThreadLevel")
    public Ghost setThreadLevel(@RequestParam String name, @RequestParam ThreadLevel threadLevel){
        Ghost ghost = ghosts.get(name);
        ghost.setThreadlevel(threadLevel);
        ghosts.put(name, ghost);
        return ghost;
    }

    @GetMapping("/ghostType")
    public List<Ghost> getGhostByType(@RequestParam GhostType ghostType){
        return ghosts.values().stream().toList().stream().filter(ghost->ghost.getGhostType().equals(ghostType)).toList();
    }

    @DeleteMapping("/ghost")
    public Ghost deleteGhost(@RequestParam String name){
        Ghost ghost = ghosts.get(name);
        ghosts.remove(name);
        return ghost;
    }




}
