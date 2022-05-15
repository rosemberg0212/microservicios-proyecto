package udc.practica.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {
    private String name;
    private String type;
    private String description;
    private Boolean state;
    private int userId;
}
