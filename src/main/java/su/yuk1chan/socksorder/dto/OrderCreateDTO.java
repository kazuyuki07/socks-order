package su.yuk1chan.socksorder.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderCreateDTO {

    @NotNull(message = "ID поставщика не может быть пустым")
    private Long producerId;

    @NotEmpty(message = "Содержимое заказа не может быть пустым")
    private List<OrderContentDTO> orderContents;
}