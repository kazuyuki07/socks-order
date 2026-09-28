package su.yuk1chan.socksorder.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrdersContentDTO {
    private Long orderId;
    private Long socksId;
    private Integer quantity;
}
