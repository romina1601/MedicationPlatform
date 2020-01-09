package repositories;

import entities.MonitoredData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<MonitoredData, Integer> {
}
