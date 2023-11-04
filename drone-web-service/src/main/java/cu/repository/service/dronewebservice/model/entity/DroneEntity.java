package cu.repository.service.dronewebservice.model.entity;

import cu.repository.service.dronewebservice.model.enums.EModels;
import cu.repository.service.dronewebservice.model.enums.EState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@ToString
@Table(name = "drones")
@NoArgsConstructor
@AllArgsConstructor
public class DroneEntity {
    @Id
    @Getter
    @Column(length = 100, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serialNumber;


    @Getter
    @Enumerated(EnumType.STRING)
    private EModels model;

    @Getter
    @Setter
    @Column(nullable = false)
    private float weight;

    @Getter
    @Setter
    @Column(nullable = false)
    private int battery;

    @Getter
    @Enumerated(EnumType.STRING)
    private EState state;

    @Getter
    @OneToMany(mappedBy = "drone")
    private Set<MedicationEntity> medications;
}
