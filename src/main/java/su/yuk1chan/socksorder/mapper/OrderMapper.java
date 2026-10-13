package su.yuk1chan.socksorder.mapper;

import org.mapstruct.*;
import su.yuk1chan.socksorder.dto.OrderContentDTO;
import su.yuk1chan.socksorder.dto.OrderCreateDTO;
import su.yuk1chan.socksorder.dto.OrderDTO;
import su.yuk1chan.socksorder.entities.Order;
import su.yuk1chan.socksorder.entities.OrderContent;
import su.yuk1chan.socksorder.enums.Status;
import su.yuk1chan.socksorder.utils.OrderNumberGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true),
        imports = { LocalDateTime.class, Status.class, OrderNumberGenerator.class }
)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(Status.ORDERED)")
    @Mapping(target = "orderNumber", expression = "java(OrderNumberGenerator.generate(LocalDateTime.now()))")
    Order orderCreateDtoToOrder(OrderCreateDTO orderCreateDTO);

    OrderContent orderContentDtoToOrderContent(OrderContentDTO orderContentDTO);

    List<OrderContent> orderContentListDtoToOrderContentList(List<OrderContentDTO> orderContentDTOs);

    OrderDTO orderToOrderDTO(Order order);

    void fullUpdateOrder(OrderDTO orderDTO, @MappingTarget Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partUpdateOrder(OrderDTO orderDTO, @MappingTarget Order order);

    @AfterMapping
    default void setOrderReference(@MappingTarget Order order) {
        if (order.getOrderContents() != null) {
            order.getOrderContents().forEach(orderContent -> orderContent.setOrder(order));
        }
    }
}
