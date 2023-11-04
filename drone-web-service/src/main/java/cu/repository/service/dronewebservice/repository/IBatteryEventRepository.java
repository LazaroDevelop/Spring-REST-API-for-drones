package cu.repository.service.dronewebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cu.repository.service.dronewebservice.model.entity.CheckBatteryEvent;

@Repository
public interface IBatteryEventRepository extends JpaRepository<CheckBatteryEvent, Long>{ 
}
