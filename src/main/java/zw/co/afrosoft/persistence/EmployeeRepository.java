package zw.co.afrosoft.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
