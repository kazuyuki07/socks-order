package su.yuk1chan.socksorder.entities;

import jakarta.persistence.*;
import lombok.*;
import su.yuk1chan.socksorder.enums.Status;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "producer_id")
    private Long producerId;

    private Status status;

    @Column(name = "socks_id")
    private Long socksId;
    private Integer quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
