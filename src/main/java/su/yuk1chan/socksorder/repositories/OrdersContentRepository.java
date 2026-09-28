package su.yuk1chan.socksorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import su.yuk1chan.socksorder.entities.OrdersContent;

public interface OrdersContentRepository extends JpaRepository<OrdersContent, Long> {
}
