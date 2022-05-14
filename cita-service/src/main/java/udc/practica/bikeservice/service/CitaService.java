package udc.practica.bikeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udc.practica.bikeservice.entity.Cita;
import udc.practica.bikeservice.repository.CitaRepository;


import java.util.List;

@Service
public class CitaService {
    @Autowired
    CitaRepository citaRepository;

    public List<Cita> getAll(){
        return citaRepository.findAll();
    }

    public Cita getCitaById(int id){
        return citaRepository.findById(id).orElse(null);
    }

    public Cita save(Cita cita){
        Cita citaNew = citaRepository.save(cita);
        return citaNew;
    }

    public List<Cita> byUserId(int userId){
        return citaRepository.findByUserId(userId);
    }
}
