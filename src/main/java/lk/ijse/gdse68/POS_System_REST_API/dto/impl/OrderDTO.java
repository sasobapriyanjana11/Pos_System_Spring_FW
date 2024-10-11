package lk.ijse.gdse68.POS_System_REST_API.dto.impl;

import lk.ijse.gdse68.POS_System_REST_API.customObj.OrderResponse;
import lk.ijse.gdse68.POS_System_REST_API.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements SuperDTO, OrderResponse {
    private String orderId;
    private LocalDate orderDate;
    private String custId;
    private BigDecimal total;
    private BigDecimal discount;
    private BigDecimal subTotal;
    private BigDecimal cash;
    private BigDecimal balance;
    private List<OrderDetailDTO> order_list;
}
