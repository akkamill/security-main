echo -n user:8697609e-a7c6-424a-943c-88231d5a0ee5 | base64

curl -H "Authorization: Basic dXNlcjo4Njk3NjA5ZS1hN2M2LTQyNGEtOTQzYy04ODIzMWQ1YTBlZTU=" http://localhost:8080/hello



NOTE You’ll find the class User in the org.springframework.security.core .userdetails package. 
It’s the builder implementation we use to create the object to represent the user. 
Also, as a general rule in this book, 
if I don’t present how to write a class in a code listing, it means Spring Security provides it.



When using the default UserDetailsService, a PasswordEncoder is also auto- configured. 
Because we overrode UserDetailsService, we also have to declare a PasswordEncoder. 

curl -u john:12345 http://localhost:8080/hello

curl -u user:123 http://localhost:8080/hello

curl -v http://localhost:8080/hello  

curl -v -H "Request-Id:12345" http://localhost:8080/hello

curl -v -H "Authorization:SD9cICjl1e" http://localhost:8080/hello

secrets vault