package cu.repository.service.dronewebservice.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import cu.repository.service.dronewebservice.model.enums.EModels;
import cu.repository.service.dronewebservice.model.enums.EState;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DroneEntity {
    @Id
    @Column(length = 100)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialNumber;

    @Enumerated(EnumType.STRING)
    private EModels model;

    private double weightLimit;

    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    private EState state;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "drone", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<MedicationEntity> medications;
}
