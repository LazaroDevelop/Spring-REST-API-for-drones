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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@ToString
@NoArgsConstructor
@Table(name = "check_battery")
public class CheckBatteryEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(length = 100, nullable = false)
    private long droneSerialNumber;

    @Setter
    @Getter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EModels droneModel;

    @Setter
    @Getter
    @Column(nullable = false)
    private int droneBatteryLevel;

    @Setter
    @Getter
    @Column(nullable = false)
    private Date timestamp;

    CheckBatteryEvent(long droneSerialNumber, EModels droneModel, int droneBatteryLevel, Date timestamp){
        this.droneSerialNumber = droneSerialNumber;
        this.droneBatteryLevel = droneBatteryLevel;
        this.droneModel = droneModel;
        this.timestamp = timestamp;
    }
}
