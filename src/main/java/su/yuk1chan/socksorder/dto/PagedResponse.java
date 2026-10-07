package su.yuk1chan.socksorder.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.*;

@Getter 
@Setter 
@EqualsAndHashCode 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString 
public class PagedResponse<T> {
    private List<T> content;
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;

    public static <T> PagedResponse<T> from(Page<T> page) {
        return new PagedResponse<>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages()
        );
    }
}
