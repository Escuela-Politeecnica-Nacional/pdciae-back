package epn.schemas;

import epn.Enums.Rol;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Document(collection = "admins")
public class Admin extends Usuario {

	public Admin(String id_usuario, String nombre, String apellido, String contraseña) {
		super(id_usuario, nombre, apellido, Rol.ADMIN, contraseña);
		setRolAdmin();
	}

	private void setRolAdmin() {
		this.setRol(Rol.ADMIN);
	}
}
