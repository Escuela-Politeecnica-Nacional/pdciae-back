package epn.schemas;

import epn.Enums.Rol;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @Getter
    @Setter
    private String id_usuario;

    @Getter
    @Setter
    private String nombre;
    
    
    @Getter
    @Setter
    private String apellido;
    
    
    @Getter
    @Setter
    private Rol rol;
    
    @Getter
    @Setter
    private String contraseña;
}
