package cu.repository.service.dronewebservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cu.repository.service.dronewebservice.model.entity.CheckBatteryEvent;

/**
 * @author Lázaro Noel Guerra
 * @version 1.0
 * @brief Check battery event repository
 */
@Repository
public interface ICheckBatteryEventRepository extends JpaRepository<CheckBatteryEvent, Long>{
    List<CheckBatteryEvent> findAll();
}
