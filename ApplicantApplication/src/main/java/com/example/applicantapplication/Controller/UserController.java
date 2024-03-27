package com.example.applicantapplication.Controller;

import com.example.applicantapplication.Entities.User;
import com.example.applicantapplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestParam("name") String name,
                                        @RequestParam("email") String email,
                                        @RequestParam("files") MultipartFile[] files) {
        try {
            if (files.length != 3) {
                return ResponseEntity.badRequest().build();
            }

            List<byte[]> fileDataList = Arrays.asList(
                    files[0].getBytes(),
                    files[1].getBytes(),
                    files[2].getBytes()
            );

            List<String> fileNames = Arrays.asList(
                    files[0].getOriginalFilename(),
                    files[1].getOriginalFilename(),
                    files[2].getOriginalFilename()
            );

            User user = new User();
            user.setName(name);
            user.setEmail(email);

            User savedUser = userService.createUser(user, fileDataList, fileNames);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
