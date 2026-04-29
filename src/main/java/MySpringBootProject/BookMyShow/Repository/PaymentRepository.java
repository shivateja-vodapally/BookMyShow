package MySpringBootProject.BookMyShow.Repository;

import MySpringBootProject.BookMyShow.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
