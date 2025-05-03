package com.example.demo.Controller;
import com.example.demo.Repository.AppUserRepository;
import com.example.demo.Models.AppUser;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final AppUserRepository repo;

    public UserController(AppUserRepository repo){
        this.repo = repo;
    }

    @GetMapping
    public List<AppUser> getAllUsers(){
        return repo.findAll();
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user){
        return repo.save(user);
    }
}
