package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t01.n03.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter

public class FlorDTO implements Serializable {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Flower Id", example = "123")
    private Long pk_FlorID;
    @Schema(description = "Flower's name", example = "Rosa")
    private String nomFlor;
    @Schema(description = "Flower's country", example = "France")
    private String paisFlor;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY,description = "Flower's pertinence to euro-zone ", example = "EURO")
    private String typeFlor;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY,description = "Array for determine the EURO or Not EURO pertinence ")
    private final ArrayList<String> euroCountries = new ArrayList<>(List.of("Alemania", "Austria", "Bélgica", "Chipre", "Croacia", "Eslovaquia", "Eslovenia", "España", "Estonia", "Finlandia", "Francia", "Grecia", "Irlanda", "Italia", "Letonia", "Lituania", "Luxemburgo", "Malta", "Países Bajos", "Portugal"));

    public FlorDTO(Long pk_FlorID, String nomFlor, String paisFlor) {
        this.pk_FlorID = pk_FlorID;
        this.nomFlor = nomFlor;
        this.paisFlor = paisFlor;
        this.typeFlor = createTypeFlor(paisFlor);
    }

    public FlorDTO() {
    }

    public String createTypeFlor(String paisFlor) {
        if (paisFlor != null) {
            if (this.euroCountries.contains(paisFlor)) {
                return "EURO";
            }
        }
        return "NO EURO";
    }
}
