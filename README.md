# Redis-Spring
This project deals with using Redis using Docker Compose via spring framework.

There are two directories in the project root: redis_data and redis.conf.

## How do I testing it:

###. Via Postnan:

Get: http://localhost:8080/redis/hello

Post: http://localhost:8080/redis/save?key=user:1

    body: 
    	{
  			"name": "John Doe",
  			"age": 30,
  			"email": "johndoe@example.com"
		}

Get: http://localhost:8080/redis/findAll

Get: http://localhost:8080/redis/find?key=user:1

Put: http://localhost:8080/redis/update?key=user:1

Delete: http://localhost:8080/redis/delete?key=user:1

###. Direct acess to Redis:

docker exec -it redis redis-cli

> ping

PONG

> keys *

1) "\xac\xed\x00\x05t\x00\x06user:1"

>get "\xac\xed\x00\x05t\x00\x06user:1"

....

get "\xac\xed\x00\x05t\x00\x06user:1"
