package su.yuk1chan.socksorder.services;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import su.yuk1chan.socksorder.dto.OrdersDTO;
import su.yuk1chan.socksorder.dto.PagedResponse;
import su.yuk1chan.socksorder.entities.Orders;
import su.yuk1chan.socksorder.enums.OrdersSort;
import su.yuk1chan.socksorder.enums.Status;
import su.yuk1chan.socksorder.exceptions.NotFoundException;
import su.yuk1chan.socksorder.mapper.OrdersMapper;
import su.yuk1chan.socksorder.repositories.OrdersRepository;
import su.yuk1chan.socksorder.repositories.specifications.OrdersSpecification;
import su.yuk1chan.socksorder.utils.OrderNumberGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;

    public PagedResponse<OrdersDTO> getOrders(
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
            Specification<Orders> ordersSpecification = Specification.where(
                OrdersSpecification.orderNumberFilter(orderNumber)
                .and(OrdersSpecification.producerIdsFilter(producerIds))
                .and(OrdersSpecification.statusFilter(status))
                .and(OrdersSpecification.socksIdsFilter(socksIds))
                .and(OrdersSpecification.quantityFilter(minQuantity, maxQuantity))
                .and(OrdersSpecification.createdAtFilter(createdAt))
            );

            OrdersSort ordersSort = OrdersSort.of(sort);

            Sort sorted = Sort.by(
                ordersSort.getDirection(),
                ordersSort.getEntityValue()
            );

            Page<Orders> orders = ordersRepository.findAll(
                ordersSpecification,
                PageRequest.of(page, size, sorted)
            );
            
            return PagedResponse.from(orders
                .map(ordersMapper::ordersToOrdersDTO)
            );

    }

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
