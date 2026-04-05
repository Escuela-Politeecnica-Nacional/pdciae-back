package epn.schemas;

import epn.Enums.Rol;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    private String id_usuario;
    private String email;
    private String nombre;
    private String apellido;
    private Rol rol;
    private String contrasena;
}
