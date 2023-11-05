package cu.repository.service.dronewebservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cu.repository.service.dronewebservice.model.entity.DroneEntity;

/**
 * @author Lázaro Noel Guerra
 * @version 1.0
 * @brief Repository for a drone functionalities
 */
@Repository
public interface IDroneRepository extends JpaRepository<DroneEntity, Long>{
    List<DroneEntity> findAll();
}
