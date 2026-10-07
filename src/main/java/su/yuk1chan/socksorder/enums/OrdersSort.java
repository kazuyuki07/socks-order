package su.yuk1chan.socksorder.enums;

import org.springframework.data.domain.Sort;

import lombok.Getter;

import java.util.stream.Stream;

@Getter 
public enum OrdersSort {
    ORDER_NUMBER_ASC("order_number", "orderNumber"),
    ORDER_NUMBER_DESC("-order_number", "orderNumber"),
    PRODUCER_IDS_ASC("producer_ids", "producerId"),
    PRODUCER_IDS_DESC("-producer_ids", "producerId"),
    SOCKS_IDS_ASC("socks_ids", "socksId"),
    SOCKS_IDS_DESC("-socks_ids", "socksId"),
    QUANTITY_ASC("quantity", "quantity"),
    QUANTITY_DESC("-quantity", "quantity");

    private final String requestValue;
    private final String entityValue;
    private final Sort.Direction direction;

    OrdersSort(String requestValue, String entityValue) {
        this.requestValue = requestValue;
        this.entityValue = entityValue;
        this.direction = requestValue.startsWith("-") ? Sort.Direction.DESC : Sort.Direction.ASC;
    }

    public static OrdersSort of(String requestValue) {
        return Stream.of(OrdersSort.values())
            .filter(e -> e.requestValue.equals(requestValue))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Вид сортировки не найден"));
    }
}
