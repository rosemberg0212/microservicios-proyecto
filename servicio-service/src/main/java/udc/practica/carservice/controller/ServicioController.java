package udc.practica.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udc.practica.carservice.entity.Servicio;
import udc.practica.carservice.service.ServicioService;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<Servicio>> getAll(){
        List<Servicio> servicios = servicioService.getAll();
        if(servicios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(servicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getById(@PathVariable("id") int id){
        Servicio servicio = servicioService.getServicioById(id);
        if(servicio == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicio);
    }

    @PostMapping
    public ResponseEntity<Servicio> save(@RequestBody Servicio servicio){
        Servicio servicioNew =servicioService.save(servicio);
        return ResponseEntity.ok(servicioNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Servicio>> getByUserId(@PathVariable("userId") int userId){
        List<Servicio> servicios = servicioService.byUserId(userId);
        if(servicios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(servicios);
    }
}
