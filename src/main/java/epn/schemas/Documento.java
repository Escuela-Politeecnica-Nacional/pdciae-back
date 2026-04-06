package epn.schemas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "documentos")
public class Documento {

    @Id
    private String id_documento;

    private String nombreDocumento;
    private String materia;
    private String facultad;

}
