package su.yuk1chan.socksorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import su.yuk1chan.socksorder.dto.OrdersDTO;
import su.yuk1chan.socksorder.entities.Orders;
import su.yuk1chan.socksorder.exceptions.NotFoundException;
import su.yuk1chan.socksorder.mapper.OrdersMapper;
import su.yuk1chan.socksorder.repositories.OrdersRepository;
import su.yuk1chan.socksorder.utils.OrderNumberGenerator;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;

    public Orders createNewOrder(OrdersDTO ordersDTO) {
        LocalDateTime dateNow = LocalDateTime.now();
        Orders newOrder = Orders.builder()
                .orderNumber(OrderNumberGenerator.generate(dateNow))
                .producerId(ordersDTO.getProducerId())
                .status(ordersDTO.getStatus())
                .socksId(ordersDTO.getSocksId())
                .quantity(ordersDTO.getQuantity())
                .createdAt(dateNow)
                .build();

        return ordersRepository.save(newOrder);
    }

    public Orders fullUpdateOrderById(Long id, OrdersDTO ordersDTO) {
        Orders foundOrder = ordersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден заказ id" + id));

        ordersMapper.fullUpdateOrder(ordersDTO, foundOrder);

        return ordersRepository.save(foundOrder);
    }

    public Orders partUpdateOrderById(Long id, OrdersDTO ordersDTO) {
        Orders foundOrder = ordersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден заказ id" + id));

        ordersMapper.partUpdateOrder(ordersDTO, foundOrder);

        return ordersRepository.save(foundOrder);
    }

    public void deleteOrderById(Long id) {
        ordersRepository.deleteById(id);
    }
}
