package udc.practica.bikeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udc.practica.bikeservice.entity.Cita;
import udc.practica.bikeservice.service.CitaService;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> getAll(){
        List<Cita> citas = citaService.getAll();
        if(citas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getById(@PathVariable("id") int id){
        Cita cita = citaService.getCitaById(id);
        if(cita == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cita);
    }

    @PostMapping
    public ResponseEntity<Cita> save(@RequestBody Cita cita){
        Cita citaNew =citaService.save(cita);
        return ResponseEntity.ok(citaNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Cita>> getByUserId(@PathVariable("userId") int userId){
        List<Cita> citas = citaService.byUserId(userId);
        if(citas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }
}
