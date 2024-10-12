package lk.ijse.gdse68.POS_System_REST_API.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.POS_System_REST_API.dao.Customerdao;
import lk.ijse.gdse68.POS_System_REST_API.dao.Itemdao;
import lk.ijse.gdse68.POS_System_REST_API.dao.OrderDetailsdao;
import lk.ijse.gdse68.POS_System_REST_API.dao.Orderdao;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.OrderDTO;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.OrderDetailDTO;
import lk.ijse.gdse68.POS_System_REST_API.entity.CustomerEntity;
import lk.ijse.gdse68.POS_System_REST_API.entity.ItemEntity;
import lk.ijse.gdse68.POS_System_REST_API.entity.OrderDetailsEntity;
import lk.ijse.gdse68.POS_System_REST_API.entity.OrderEntity;
import lk.ijse.gdse68.POS_System_REST_API.exception.DataPersistFailedException;
import lk.ijse.gdse68.POS_System_REST_API.service.PlaceOrderService;
import lk.ijse.gdse68.POS_System_REST_API.util.AppUtill;
import lk.ijse.gdse68.POS_System_REST_API.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@Transactional
public class PlaceOrderServiceImpl implements PlaceOrderService {
    @Autowired
    private Customerdao customerdao;
    @Autowired
    private Itemdao itemDao;
    @Autowired
    private Orderdao orderdao;
    @Autowired
    private OrderDetailsdao orderDetailsdao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        // Create and populate the OrderEntity
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(AppUtill.createOrderId());
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setTotal(orderDTO.getTotal());
        orderEntity.setDiscount(orderDTO.getDiscount());
        orderEntity.setSubTotal(orderDTO.getSubTotal());
        orderEntity.setCash(orderDTO.getCash());
        orderEntity.setBalance(orderDTO.getBalance());

        // Retrieve the CustomerEntity from the database
        CustomerEntity customerEntity = customerdao.findById(orderDTO.getCustId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer id: " + orderDTO.getCustId()));
        orderEntity.setCustomer(customerEntity);

        // Save the OrderEntity
        OrderEntity savedOrder = orderdao.save(orderEntity);

        // Process the order details and save them
        for (OrderDetailDTO orderDetailDTO : orderDTO.getOrder_list()) {
            OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
            orderDetailsEntity.setOd_id(AppUtill.createOrderDetailId());
            orderDetailsEntity.setQty(orderDetailDTO.getQty());
            orderDetailsEntity.setUnitPrice(orderDetailDTO.getUnit_price());

            // Retrieve the ItemEntity from the database
            ItemEntity itemEntity = itemDao.findById(orderDetailDTO.getItemCode())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid item code: " + orderDetailDTO.getItemCode()));
            orderDetailsEntity.setItem(itemEntity);
            orderDetailsEntity.setOrder(savedOrder);  // Link the saved order

            // Save each OrderDetailsEntity
            orderDetailsdao.save(orderDetailsEntity);
        }

        if (savedOrder == null) {
            throw new DataPersistFailedException("Order save failed!");
        }
    }

    @Override
    public List<OrderDetailDTO> getOrderDetails() {
        return mapping.convertOrderDetailEntityListToOrderDetailDTOList(orderDetailsdao.findAll());
    }
}
