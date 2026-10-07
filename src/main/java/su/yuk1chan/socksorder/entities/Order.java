package su.yuk1chan.socksorder.entities;

import jakarta.persistence.*;
import lombok.*;
import su.yuk1chan.socksorder.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "producer_id")
    private Long producerId;

    private Status status;

    @OneToMany(targetEntity = OrderContent.class, fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderContent> orderContents;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
