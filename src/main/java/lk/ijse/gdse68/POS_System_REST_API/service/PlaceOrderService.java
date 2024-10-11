package lk.ijse.gdse68.POS_System_REST_API.service;

import lk.ijse.gdse68.POS_System_REST_API.dto.impl.OrderDTO;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.OrderDetailDTO;

import java.util.List;

public interface PlaceOrderService {
    void saveOrder(OrderDTO orderDTO);

    List<OrderDetailDTO> getOrderDetails();
}
