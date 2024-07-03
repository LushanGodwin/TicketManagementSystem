package lk.ijse.userservice.controller;

import jakarta.ws.rs.NotFoundException;
import lk.ijse.userservice.dto.LoginDTO;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class User {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    @GetMapping("/health")
    public String UserCheck(){
        return "Hello I'm User Controller. I'm OK! Have a nice day!";
    }

    @GetMapping("/login")public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO){
       try {
           userService.login(loginDTO);
           return ResponseEntity.ok("User logged in successfully.");

       }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Login Failed.\nMore Details\n"+exception);
       }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(@Validated @RequestBody UserDTO userDTO,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | User saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> getUser(@PathVariable ("id") String id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping(value = "/{id}",produces = "application/json")
    public boolean deleteUser(@PathVariable("id") String id){
        return userService.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable("id") String id,@Validated @RequestBody UserDTO userDTO,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            userService.updateUser(id,userDTO);
            return ResponseEntity.status(HttpStatus.OK).body("User Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | User Details Updated Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @GetMapping("/userExists/{userId}")
    public ResponseEntity<?> isUserExists(@PathVariable String userId) {
        logger.info("Checking user existence with ID: {}", userId);
        try {
            boolean isUserExists = userService.isUserExists(userId);
            logger.info("User Exists: {}", isUserExists);
            return ResponseEntity.ok(isUserExists);
        } catch (Exception exception) {
            logger.error("Error checking user existence: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to check user existence.\nMore Details\n" + exception);
        }
    }

}
