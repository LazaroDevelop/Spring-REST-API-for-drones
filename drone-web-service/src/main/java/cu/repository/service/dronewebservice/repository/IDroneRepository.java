package cu.repository.service.dronewebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cu.repository.service.dronewebservice.model.entity.DroneEntity;

@Repository
public interface IDroneRepository extends JpaRepository<DroneEntity, Long>{
}
