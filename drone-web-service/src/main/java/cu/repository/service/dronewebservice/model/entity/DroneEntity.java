package cu.repository.service.dronewebservice.model.entity;

import cu.repository.service.dronewebservice.model.enums.Models;
import cu.repository.service.dronewebservice.model.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "drones")
@NoArgsConstructor
@AllArgsConstructor
public class DroneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    @Getter
    @Setter
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private Models model;

    @Getter
    @Setter
    @Column(nullable = false)
    private float weight;

    @Getter
    @Setter
    @Column(nullable = false)
    private int battery;

    @Enumerated(EnumType.STRING)
    private State state;
}
