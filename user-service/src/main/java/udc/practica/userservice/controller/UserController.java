package udc.practica.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udc.practica.userservice.entity.User;
import udc.practica.userservice.model.Cita;
import udc.practica.userservice.model.Servicio;
import udc.practica.userservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/servicios/{userId}")
    public ResponseEntity<List<Servicio>> getServicios(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Servicio> servicios = userService.getServicio(userId);
        return ResponseEntity.ok(servicios);
    }

    @GetMapping("/citas/{userId}")
    public ResponseEntity<List<Cita>> getCitas(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Cita> citas = userService.getCita(userId);
        return ResponseEntity.ok(citas);
    }

    @PostMapping("/savecita/{userId}")
    public ResponseEntity<Cita> saveCita(@PathVariable("userId") int userId, @RequestBody Cita cita){
        if(userService.getUserById(userId) == null){
            return ResponseEntity.notFound().build();
        }
        Cita citaNew = userService.saveCita(userId, cita);
        return ResponseEntity.ok(cita);
    }

    @PostMapping("/saveservicio/{userId}")
    public ResponseEntity<Servicio> saveServicio(@PathVariable("userId") int userId, @RequestBody Servicio servicio){
        if(userService.getUserById(userId) == null){
            return ResponseEntity.notFound().build();
        }
        Servicio servicioNew = userService.saveServicio(userId, servicio);
        return ResponseEntity.ok(servicio);
    }
}
