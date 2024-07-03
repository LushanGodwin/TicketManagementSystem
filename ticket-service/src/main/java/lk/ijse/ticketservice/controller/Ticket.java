package lk.ijse.ticketservice.controller;

import lk.ijse.ticketservice.Enum.Status;
import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.service.TicketService;
import lk.ijse.ticketservice.service.UserClientService;
import lk.ijse.ticketservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class Ticket {
    private final TicketService ticketService;
    private final VehicleService vehicleService;
    private final UserClientService userClientService;
    private static final Logger logger = LoggerFactory.getLogger(Ticket.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTicket(@RequestBody TicketDTO ticketDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!userClientService.isExitsUser(ticketDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }
        if (!vehicleService.isExitsVehicle(ticketDTO.getVehicleId())) {
            return ResponseEntity.badRequest().body("Vehicle not found");
        }
        System.out.println(LocalDate.now());
        ticketDTO.setTicketIssueDate(LocalDate.now());
        ticketDTO.setStatus(Status.NON_PAID);
        ticketService.saveTicket(ticketDTO);
        return ResponseEntity.ok("Ticket saved successfully");
    }

    @GetMapping(value = "/{ticketId}")
    public ResponseEntity<?> getTicket(@PathVariable ("ticketId") String ticketId){
        return ResponseEntity.ok(ticketService.getTicket(ticketId));
    }

    @GetMapping
    public ResponseEntity<?> getAllTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTicket(@RequestBody TicketDTO ticketDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!userClientService.isExitsUser(ticketDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }
        if (!vehicleService.isExitsVehicle(ticketDTO.getVehicleId())) {
            return ResponseEntity.badRequest().body("Vehicle not found");
        }
        ticketDTO.setStatus(Status.NON_PAID);
        ticketDTO.setTicketIssueDate(LocalDate.now());
        ticketService.updateTicket(ticketDTO);
        return ResponseEntity.ok("Ticket updated successfully");
    }

    @DeleteMapping(value = "/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable ("ticketId") String ticketId){
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok("Ticket deleted successfully");
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<?> getTicketsByUserId(@PathVariable("userId") String userId){
        if (!userClientService.isExitsUser(userId)){
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(ticketService.getTicketsByUserId(userId));
    }

    @GetMapping(value = "/vehicle/{vehicleId}")
    public ResponseEntity<?> getTicketByVehicleId(@PathVariable ("vehicleId") String vehicleId){
        if (!vehicleService.isExitsVehicle(vehicleId)){
            return ResponseEntity.badRequest().body("Vehicle not found");
        }
        return ResponseEntity.ok(ticketService.getTicketsByVehicleId(vehicleId));
    }

    @GetMapping("/ticketExists")
    public ResponseEntity<?> isTicketExists(@RequestParam String ticketId) {
        System.out.println(ticketId);
        logger.info("Checking ticket existence with ID: {}", ticketId);
        try {
            boolean isTicketExists = ticketService.isTicketExists(ticketId);
            logger.info("Ticket Exists: {}", isTicketExists);
            System.out.println(isTicketExists);
            return ResponseEntity.ok(isTicketExists);
        } catch (Exception exception) {
            logger.error("Error checking ticket existence: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to check ticket existence.\nMore Details\n" + exception);
        }
    }
}
