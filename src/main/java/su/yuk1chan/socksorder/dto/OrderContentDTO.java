package su.yuk1chan.socksorder.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderContentDTO {

    @NotNull(message = "ID носка не может быть пустым")
    private Long socksId;

    @NotNull(message = "Количество не может быть пустым")
    @Min(value = 0, message = "Количество не может быть отрицательным")
    private Integer quantity;
}
