package lk.ijse.userservice.controller;

import jakarta.ws.rs.NotFoundException;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class User {

    private final UserService userService;
    @GetMapping("/health")
    public String UserCheck(){
        return "Hello I'm User Controller. I'm OK! Have a nice day!";
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


}
