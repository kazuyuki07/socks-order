package su.yuk1chan.socksorder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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
    @NotBlank(message = "Номер заказа не может быть пустым")
    private String orderNumber;

    @NotNull(message = "ID поставщика не может быть пустым")
    private Long producerId;

    @NotNull(message = "Статус не может быть пустым")
    private Status status;

    @NotEmpty(message = "Содержимое заказа не может быть пустым")
    private List<OrderContentDTO> orderContents;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    @NotNull(message = "Дата создания не может быть пустой")
    private LocalDateTime createdAt;
}
