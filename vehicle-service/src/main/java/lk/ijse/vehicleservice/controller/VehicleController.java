package lk.ijse.vehicleservice.controller;

import jakarta.ws.rs.NotFoundException;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.service.UserServiceClient;
import lk.ijse.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;

    private final UserServiceClient userServiceClient;

    @GetMapping("/check")
    public String VehicleCheck(){
        return "Hello I'm Vehicle Controller. I'm OK! Have a nice day!";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveVehicle(@RequestBody VehicleDTO vehicleDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!userServiceClient.isExitsUser(vehicleDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }
        System.out.println(vehicleDTO.getVehicleCode());
        vehicleService.saveVehicle(vehicleDTO);
        return ResponseEntity.ok("Vehicle saved successfully");
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> getVehicle(@PathVariable ("id") String id){
        return ResponseEntity.ok(vehicleService.getVehicle(id));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @DeleteMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") String id){
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateVehicle(@PathVariable("id") String id,@Validated @RequestBody VehicleDTO vehicleDTO,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            vehicleService.updateVehicle(id,vehicleDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Vehicle Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Vehicle Details Updated Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVehiclesByUserId(@PathVariable ("userId") String userId){
        return ResponseEntity.ok(vehicleService.getVehicleByUserId(userId));
    }
}
