package repositories;

import entities.GrpcMedication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrpcMedicationRepository extends JpaRepository<GrpcMedication, Integer> {
}
