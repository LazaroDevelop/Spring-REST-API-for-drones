package cu.repository.service.dronewebservice.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * @author Lázaro Noel Guerra
 * @version 1.0
 */

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MedicationEntity {
    /**
     * Medication id {Primary key of the medication table}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Medication name
     */
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
    private String name;

    /**
     * Medication weight
     */
    private double weight;

    /**
     * Medication code
     */
    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;

    /**
     * Medication image
     */
    private String image;

    /**
     * Drone reference
     */
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drone_id", nullable = false)
    private DroneEntity drone;
}
