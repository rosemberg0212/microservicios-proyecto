package udc.practica.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    private String typeService;
    private String date;
    private String moment;
    private int userId;
}
