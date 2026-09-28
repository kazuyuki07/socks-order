package su.yuk1chan.socksorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import su.yuk1chan.socksorder.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
