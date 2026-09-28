package su.yuk1chan.socksorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import su.yuk1chan.socksorder.repositories.OrdersRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;

}
