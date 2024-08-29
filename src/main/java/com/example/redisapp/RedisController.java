package com.example.redisapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {
	
    @Autowired
    private RedisService redisService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam String key, @RequestBody Object value) {
        redisService.save(key, value);
        return ResponseEntity.ok("Saved successfully!");
    }

    @GetMapping("/find")
    public Object find(@RequestParam String key) {
        return redisService.find(key);
    }
    
    /**
     * Since Redis does not have a built-in method to retrieve all keys directly, you can use 
     * the keys method to get all keys that match a specific pattern. However, keep in mind 
     * that using the keys command in a production environment is discouraged for performance 
     * reasons, as it can block the server if there are many keys.
     */
    @GetMapping("/findAll")
    public Object findAll() {
        return redisService.findAll();
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam String key, @RequestBody Object value) {
        // Check if the key exists before updating
        if (redisService.find(key) == null) {
            return ResponseEntity.notFound().build(); // Return 404 if the key does not exist
        }
        
        redisService.save(key, value); // Save the updated value
        return ResponseEntity.ok("Value updated successfully for key: " + key);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String key) {
        // Check if the key exists before deleting
        if (redisService.find(key) == null) {
            return ResponseEntity.notFound().build(); // Return 404 if the key does not exist
        }
        
        redisService.delete(key); // Call the delete method in RedisService
        return ResponseEntity.ok("Value deleted successfully for key: " + key);
    }    
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }
    
}