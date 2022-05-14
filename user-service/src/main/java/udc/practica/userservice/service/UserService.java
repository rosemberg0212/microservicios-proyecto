package udc.practica.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import udc.practica.userservice.entity.User;
import udc.practica.userservice.feignClients.CitaFeignClient;
import udc.practica.userservice.feignClients.ServicioFeignClient;
import udc.practica.userservice.model.Cita;
import udc.practica.userservice.model.Servicio;
import udc.practica.userservice.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CitaFeignClient citaFeignClient;

    @Autowired
    ServicioFeignClient servicioFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        User userNew = userRepository.save(user);
        return userNew;
    }

    public List<Servicio> getServicio(int userId){
        List<Servicio> servicios = restTemplate.getForObject("http://localhost:8002/servicios/byuser/" + userId, List.class);
       return servicios;
    }

    public List<Cita> getCita(int userId){
        List<Cita> citas = restTemplate.getForObject("http://localhost:8003/citas/byuser/" + userId, List.class);
        return citas;
    }

    public Cita saveCita(int userId, Cita cita){
        cita.setUserId(userId);
        Cita citaNew = citaFeignClient.save(cita);
        return citaNew;
    }

    public Servicio saveServicio(int userId, Servicio servicio){
        servicio.setUserId(userId);
        Servicio servicioNew = servicioFeignClient.save(servicio);
        return servicioNew;
    }
}
