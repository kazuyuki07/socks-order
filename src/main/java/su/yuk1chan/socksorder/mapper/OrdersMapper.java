package su.yuk1chan.socksorder.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import su.yuk1chan.socksorder.dto.OrdersDTO;
import su.yuk1chan.socksorder.entities.Orders;

@Mapper(componentModel = "spring")
public interface OrdersMapper {
    OrdersDTO ordersToOrdersDTO(Orders orders);
    void fullUpdateOrder(OrdersDTO ordersDTO, @MappingTarget Orders orders);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partUpdateOrder(OrdersDTO ordersDTO, @MappingTarget Orders orders);
}
