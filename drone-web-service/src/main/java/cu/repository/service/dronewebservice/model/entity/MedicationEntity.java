package cu.repository.service.dronewebservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicationw")
public class MedicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
    private String name;

    @Column(nullable = false)
    @Getter
    @Setter
    private float weight;

    @Column(nullable = false)
    @Getter
    @Setter
    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;

    @Column(nullable = false)
    @Getter
    @Setter
    private String image;

    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    @Getter
    @Setter
    private DroneEntity drone;
}
