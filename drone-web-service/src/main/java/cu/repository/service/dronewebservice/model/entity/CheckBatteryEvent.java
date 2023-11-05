package cu.repository.service.dronewebservice.model.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cu.repository.service.dronewebservice.model.enums.EModels;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CheckBatteryEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private long droneSerialNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EModels droneModel;

    @Column(nullable = false)
    private int droneBatteryLevel;

    @Column(nullable = false)
    private Date timestamp;
}
