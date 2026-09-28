package su.yuk1chan.socksorder.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import su.yuk1chan.socksorder.repositories.OrdersContentRepository;

@Service
@RequiredArgsConstructor
public class OrdersContentService {
    private final OrdersContentRepository ordersContentRepository;
}
