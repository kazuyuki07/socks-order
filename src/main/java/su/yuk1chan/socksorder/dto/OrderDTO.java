package su.yuk1chan.socksorder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import su.yuk1chan.socksorder.entities.OrderContent;
import su.yuk1chan.socksorder.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderDTO {
    private String orderNumber;
    private Long producerId;
    private Status status;
    private List<OrderContent> orderContents;
    private Long socksId;
    private Integer quantity;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime createdAt;
}
