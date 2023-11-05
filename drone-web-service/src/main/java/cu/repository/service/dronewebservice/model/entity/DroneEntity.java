package cu.repository.service.dronewebservice.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import cu.repository.service.dronewebservice.model.enums.EModels;
import cu.repository.service.dronewebservice.model.enums.EState;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
public class DroneEntity {
    /**
     * Serial number of one drone {Primary key of the table}
     */
    @Id
    @Column(length = 100)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialNumber;

    /**
     * Drone model
     */
    @Enumerated(EnumType.STRING)
    private EModels model;

    /**
     * Max weight support by the drone
     */
    private double weightLimit;

    /**
     * Drone battery capacity
     */
    private int batteryCapacity;

    /**
     * Drone action state
     */
    @Enumerated(EnumType.STRING)
    private EState state;

    /**
     * Drone medications set
     */
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "drone", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<MedicationEntity> medications;
}
