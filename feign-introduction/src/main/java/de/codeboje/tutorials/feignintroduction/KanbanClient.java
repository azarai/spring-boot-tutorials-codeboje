package de.codeboje.tutorials.feignintroduction;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import de.codeboje.tutorials.feignintroduction.model.Board;
import de.codeboje.tutorials.feignintroduction.model.Confirmation;
import de.codeboje.tutorials.feignintroduction.model.User;

@FeignClient(name="KanbanClient", url= "https://kanbanbackend.herokuapp.com/")
public interface KanbanClient {

    @PostMapping(value = "/register")
    String registerUser(User user);
    
    @DeleteMapping("/unregister")
    ResponseEntity<Void> unregisterUser(@RequestHeader("X-Auth-Token") String authToken, Confirmation user);
    
    @PostMapping("/login")
    ResponseEntity<Void> loginUser(@RequestHeader("Authorization") String authHeader);
    
    @GetMapping("/boards")
    List<Board> listBoards(@RequestHeader("X-Auth-Token") String authHeader);
    
    @PostMapping("/boards")
    Board createBoard(@RequestHeader("X-Auth-Token") String authHeader, Board board);
    
    @PutMapping("/board/{id}")
    Board changeBoard(@RequestHeader("X-Auth-Token") String authHeader, @PathVariable("id") Long id, Board board);
}
