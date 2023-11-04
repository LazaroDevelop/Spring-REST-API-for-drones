package cu.repository.service.dronewebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cu.repository.service.dronewebservice.model.entity.DroneEntity;

public interface IDroneRepository extends JpaRepository<DroneEntity, Long>{
}
