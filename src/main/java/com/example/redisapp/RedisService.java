package com.example.redisapp;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object find(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    public Map<String, Object> findAll() {
        // Get all keys from Redis
        Set<String> keys = redisTemplate.keys("user:*"); // Use a specific pattern if needed
        if (keys == null || keys.isEmpty()) {
            return Map.of(); // Return an empty map if there are no keys
        }

        // Fetch all values for the retrieved keys
        return keys.stream()
                   .collect(Collectors.toMap(key -> key, this::find));
    }

    public void delete(String key) {
        redisTemplate.delete(key); // Delete the key from Redis
    }
    
}