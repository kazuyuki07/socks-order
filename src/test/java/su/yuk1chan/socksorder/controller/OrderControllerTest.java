package su.yuk1chan.socksorder.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import su.yuk1chan.socksorder.dto.OrderContentDTO;
import su.yuk1chan.socksorder.dto.OrderCreateDTO;
import su.yuk1chan.socksorder.repositories.OrderRepository;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void cleanData() {
        orderRepository.deleteAll();
    }

    @Test
    void createNewOrderSuccess() throws Exception {
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO(
                1L,
                List.of(new OrderContentDTO(
                        1L,
                        100
                ))
        );

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.producerId").value(orderCreateDTO.getProducerId()))
                .andExpect(jsonPath("$.status").value("ORDERED"))
                .andExpect(jsonPath("$.orderContents[0].socksId").value(1))
                .andExpect(jsonPath("$.orderContents[0].quantity").value(100))
                .andExpect(jsonPath("$.createdAt").isNotEmpty());
    }
}
