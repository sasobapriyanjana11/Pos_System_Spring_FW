package lk.ijse.gdse68.POS_System_REST_API.dto.impl;

import lk.ijse.gdse68.POS_System_REST_API.customObj.OrderDetailResponse;
import lk.ijse.gdse68.POS_System_REST_API.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO implements SuperDTO, OrderDetailResponse {
    private String orderId;
    private String itemCode;
    private BigDecimal unit_price;
    private int qty;
}
