package su.yuk1chan.socksorder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import su.yuk1chan.socksorder.dto.OrderDTO;
import su.yuk1chan.socksorder.dto.PagedResponse;
import su.yuk1chan.socksorder.entities.Order;
import su.yuk1chan.socksorder.enums.Status;
import su.yuk1chan.socksorder.mapper.OrderMapper;
import su.yuk1chan.socksorder.services.OrderService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping()
    public PagedResponse<OrderDTO> getOrders(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String orderNumber,
            @RequestParam(required = false) List<Long> producerIds,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) List<Long> socksIds,
            @RequestParam(required = false) Integer minQuantity,
            @RequestParam(required = false) Integer maxQuantity,
            @RequestParam(required = false) LocalDate createdAt,
            @RequestParam(defaultValue = "-created_at") String sort) {
        return orderService.getOrders(
                page,
                size,
                orderNumber,
                producerIds,
                status,
                socksIds,
                minQuantity,
                maxQuantity,
                createdAt,
                sort
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createNewOrder(@RequestBody OrderDTO orderDTO) {
        Order newOrder = orderService.createNewOrder(orderDTO);

        return orderMapper.orderToOrderDTO(newOrder);
    }

    @PutMapping("/{orderId}")
    public OrderDTO fullUpdateOrderById(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        Order updatedOrder = orderService.fullUpdateOrderById(orderId, orderDTO);

        return orderMapper.orderToOrderDTO(updatedOrder);
    }

    @PatchMapping("/{orderId}")
    public OrderDTO partUpdateOrderById(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        Order patchUpdatedOrder = orderService.partUpdateOrderById(orderId, orderDTO);

        return orderMapper.orderToOrderDTO(patchUpdatedOrder);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderById(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
    }
}
