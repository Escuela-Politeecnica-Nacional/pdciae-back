package epn.schemas;

import epn.Enums.Rol;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Document(collection = "estudiantes")
public class Estudiante extends Usuario {

    public Estudiante(String id_usuario, String nombre, String apellido, String contraseña) {
        super(id_usuario, nombre, apellido, Rol.ESTUDIANTE, contraseña);
        setRolEstudiante();
    }

    private void setRolEstudiante(){
        this.setRol(Rol.ESTUDIANTE);
    } 

}
