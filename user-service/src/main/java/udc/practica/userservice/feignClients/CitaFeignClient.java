package udc.practica.userservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import udc.practica.userservice.model.Cita;

import java.util.List;

@FeignClient(name = "cita-service", url = "http://localhost:8003", path = "/citas")
//@RequestMapping("/citas")
public interface CitaFeignClient {

    @PostMapping()
    Cita save(@RequestBody Cita cita);

    @GetMapping("/byuser/{userId}")
    List<Cita> getCitas(@PathVariable("userId") int userId);
}
