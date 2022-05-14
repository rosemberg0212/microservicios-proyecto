package udc.practica.userservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import udc.practica.userservice.model.Servicio;

import java.util.List;

@FeignClient(name = "servicio-service", url = "http://localhost:8002", path = "/servicios")
public interface ServicioFeignClient {

    @PostMapping()
    Servicio save(@RequestBody Servicio servicio);

    @GetMapping("/byuser/{userId}")
    List<Servicio> getServicios(@PathVariable("userId") int userId);
}
