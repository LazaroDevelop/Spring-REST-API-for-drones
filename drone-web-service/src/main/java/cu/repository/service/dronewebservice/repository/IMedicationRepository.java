package cu.repository.service.dronewebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cu.repository.service.dronewebservice.model.entity.MedicationEntity;

/**
 * @author Lázaro Noel Guerra
 * @version 1.0
 * @brief Repository for medication functionalities
 */
@Repository
public interface IMedicationRepository extends JpaRepository<MedicationEntity, Long>{
}
