package com.example.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;
   
@PostMapping("/register")
      public ResponseEntity<?> registerUser(@RequestBody Account account) {
        try {
           
            Account registeredAccount = accountService.NewregisterHandle(account);
    
            if (registeredAccount == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
            } else {
                return ResponseEntity.ok(registeredAccount); 
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(" "); 
        }
          
    }
    @PostMapping("/login")
      public ResponseEntity<?> UserLoginHandle(@RequestBody Account account) {
        try {
           
            Account loginAccount = accountService.UserLogin(account.getUsername(), account.getPassword());

            if (loginAccount != null) {
                return ResponseEntity.ok(loginAccount);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Bad Request");
        }
      
    }

    @PostMapping("/messages")
    public  ResponseEntity<?> CreateMessage(@RequestBody Message message ) {

       try {
        
        Message createdMessage = messageService.CreateMessage(message);

            if (createdMessage != null) {
                return ResponseEntity.ok(createdMessage);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" ");
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> message = messageService.getAllMessage();
        return ResponseEntity.ok(message);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getMessageById(@PathVariable int messageId) {
       
          try{
            Message message = messageService.getMessageById(messageId);

            if (message != null) {
                return ResponseEntity.ok(message);
            } else {
                return ResponseEntity.ok().build();
            }
       } catch (NumberFormatException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid message ID format.");
      }
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> DeleteMessage(@PathVariable int messageId) {
         try{
            
            Message deleted = messageService.DeleteMessage(messageId);

            if (deleted !=null) {
                return ResponseEntity.ok(1);
            } else {
                return ResponseEntity.ok().build();
            }
        } catch (NumberFormatException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid format");
        }
    }
@PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> UpdatedMessage(@PathVariable int messageId, @RequestBody Message updatedMessage) {
        try {
            
            Message updated = messageService.UpdatedMessage(messageId, updatedMessage.getMessageText());
            if(updated != null){

            return ResponseEntity.ok(1);
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Not Found");
            }
        
        
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format.");
        }
    }
@GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable int accountId ) {
        
            List<Message> messages = messageService.getMessageByUser(accountId);
            return ResponseEntity.ok(messages);
        } 

}

