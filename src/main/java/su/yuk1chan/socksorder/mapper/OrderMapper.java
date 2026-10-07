package su.yuk1chan.socksorder.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import su.yuk1chan.socksorder.dto.OrderDTO;
import su.yuk1chan.socksorder.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO orderToOrderDTO(Order order);
    void fullUpdateOrder(OrderDTO orderDTO, @MappingTarget Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partUpdateOrder(OrderDTO orderDTO, @MappingTarget Order order);
}
