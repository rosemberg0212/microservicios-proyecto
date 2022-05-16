package udc.practica.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
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

    public List getServicio(int userId){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer" + jwt.getTokenValue());
        ResponseEntity<List> servicios = restTemplate.exchange("http://servicio-service/servicios/byuser/" + userId, HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class);
       return servicios.getBody();
    }

    public List getCita(int userId){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<List> citas = restTemplate.exchange("http://cita-service/citas/byuser/" + userId, HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class);
        return citas.getBody();
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
