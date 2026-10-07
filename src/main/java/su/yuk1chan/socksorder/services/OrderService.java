package su.yuk1chan.socksorder.services;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import su.yuk1chan.socksorder.dto.OrderDTO;
import su.yuk1chan.socksorder.dto.PagedResponse;
import su.yuk1chan.socksorder.entities.Order;
import su.yuk1chan.socksorder.enums.OrderSort;
import su.yuk1chan.socksorder.enums.Status;
import su.yuk1chan.socksorder.exceptions.NotFoundException;
import su.yuk1chan.socksorder.mapper.OrderMapper;
import su.yuk1chan.socksorder.repositories.OrderRepository;
import su.yuk1chan.socksorder.repositories.specifications.OrderSpecification;
import su.yuk1chan.socksorder.utils.OrderNumberGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public PagedResponse<OrderDTO> getOrders(
        Integer page,
        Integer size,
        String orderNumber,
        List<Long> producerIds,
        Status status,
        List<Long> socksIds,
        Integer minQuantity,
        Integer maxQuantity,
        LocalDate createdAt,
        String sort) {
            Specification<Order> ordersSpecification = Specification.where(
                OrderSpecification.orderNumberFilter(orderNumber)
                .and(OrderSpecification.producerIdsFilter(producerIds))
                .and(OrderSpecification.statusFilter(status))
                .and(OrderSpecification.socksIdsFilter(socksIds))
                .and(OrderSpecification.quantityFilter(minQuantity, maxQuantity))
                .and(OrderSpecification.createdAtFilter(createdAt))
            );

            OrderSort orderSort = OrderSort.of(sort);
            Sort sorted = Sort.by(
                orderSort.getDirection(),
                orderSort.getEntityValue()
            );

            Page<Order> orders = orderRepository.findAll(
                ordersSpecification,
                PageRequest.of(page, size, sorted)
            );
            
            return PagedResponse.from(orders
                .map(orderMapper::orderToOrderDTO)
            );

    }

    public Order createNewOrder(OrderDTO orderDTO) {
        LocalDateTime dateNow = LocalDateTime.now();
        Order newOrder = Order.builder()
                .orderNumber(OrderNumberGenerator.generate(dateNow))
                .producerId(orderDTO.getProducerId())
                .status(orderDTO.getStatus())
                .orderContents(orderDTO.getOrderContents())
                .createdAt(dateNow)
                .build();

        return orderRepository.save(newOrder);
    }

    public Order fullUpdateOrderById(Long id, OrderDTO orderDTO) {
        Order foundOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден заказ id" + id));

        orderMapper.fullUpdateOrder(orderDTO, foundOrder);

        return orderRepository.save(foundOrder);
    }

    public Order partUpdateOrderById(Long id, OrderDTO orderDTO) {
        Order foundOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден заказ id" + id));

        orderMapper.partUpdateOrder(orderDTO, foundOrder);

        return orderRepository.save(foundOrder);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
