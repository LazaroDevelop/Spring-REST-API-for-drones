package cu.repository.service.dronewebservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medication")
public class MedicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @Getter
    @Setter
    private float weight;

    @Column(nullable = false)
    @Getter
    @Setter
    private String code;

    @Column(nullable = false)
    @Getter
    @Setter
    private String image;

    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    private DroneEntity drone;
}
