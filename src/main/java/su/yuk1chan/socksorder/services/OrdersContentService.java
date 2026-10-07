package su.yuk1chan.socksorder.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import su.yuk1chan.socksorder.dto.OrdersContentDTO;
import su.yuk1chan.socksorder.entities.OrdersContent;
import su.yuk1chan.socksorder.exceptions.NotFoundException;
import su.yuk1chan.socksorder.repositories.OrdersContentRepository;

@Service
@RequiredArgsConstructor
public class OrdersContentService {
    private final OrdersContentRepository ordersContentRepository;

    public OrdersContent addOrdersContentByOrderId(OrdersContentDTO ordersContentDTO) {
        Long orderId = ordersContentDTO.getOrderId();
        if (ordersContentRepository.existsById(orderId)) {
            throw new NotFoundException("Не найден заказ id " + orderId);
        }

        return ordersContentRepository.save(
            OrdersContent.builder()
                .orderId(orderId)
                .socksId(ordersContentDTO.getSocksId())
                .quantity(ordersContentDTO.getQuantity())
                .build()
        );
    }
}
