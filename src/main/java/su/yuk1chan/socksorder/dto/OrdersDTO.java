package su.yuk1chan.socksorder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import su.yuk1chan.socksorder.enums.Status;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrdersDTO {
    private String orderNumber;
    private Long producerId;
    private Status status;
    private Long socksId;
    private Integer quantity;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime createdAt;
}
