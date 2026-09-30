package su.yuk1chan.socksorder.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import su.yuk1chan.socksorder.entities.Orders;
import su.yuk1chan.socksorder.enums.Status;

import java.time.LocalDate;
import java.util.List;

public class OrdersSpecification {
    public static Specification<Orders> orderNumberFilter(String orderNumber) {
        return (root, _, builder) ->
                orderNumber == null || orderNumber.isEmpty() ? null : builder.like(
                        builder.lower(root.get("orderNumber")),
                        "%" + orderNumber.trim().toLowerCase() + "%"
                );
    }

    public static Specification<Orders> producerIdsFilter(List<Long> producerIds) {
        return (root, _, _) ->
            producerIds == null || producerIds.isEmpty() ? null : root.get("producerId").in(producerIds);
    }

    public static Specification<Orders> statusFilter(Status status) {
        return (root, _, builder) ->
                status == null ? null : builder.equal(root.get("status"), status);
    }

    public static Specification<Orders> socksIdsFilter(List<Long> socksIds) {
        return (root, _, _) ->
                socksIds == null || socksIds.isEmpty() ? null : root.get("socksId").in(socksIds);
    }

    public static Specification<Orders> quantityFilter(Integer minQuantity, Integer maxQuantity) {
        return (root, _, builder) -> {
            if (minQuantity == null && maxQuantity == null) {
                return null;
            }

            if (minQuantity == null) {
                return builder.lessThanOrEqualTo(root.get("quantity"), maxQuantity);
            }

            if (maxQuantity == null) {
                return builder.greaterThanOrEqualTo(root.get("quantity"), minQuantity);
            }

            return builder.between(root.get("quantity"), minQuantity, maxQuantity);
        };
    }

    public static Specification<Orders> dateFilter(LocalDate createdAt) {
        return (root, _, builder) ->
            createdAt == null ? null : builder.greaterThanOrEqualTo(root.get("createdAt"), createdAt);
    }
}
