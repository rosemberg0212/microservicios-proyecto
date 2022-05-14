package udc.practica.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udc.practica.carservice.entity.Servicio;
import udc.practica.carservice.repository.ServicioRepository;

import java.util.List;

@Service
public class ServicioService {
    @Autowired
    ServicioRepository servicioRepository;

    public List<Servicio> getAll(){
        return servicioRepository.findAll();
    }

    public Servicio getServicioById(int id){
        return servicioRepository.findById(id).orElse(null);
    }

    public Servicio save(Servicio servicio){
        Servicio servicioNew = servicioRepository.save(servicio);
        return servicioNew;
    }

    public List<Servicio> byUserId(int userId){
        return servicioRepository.findByUserId(userId);
    }
}
