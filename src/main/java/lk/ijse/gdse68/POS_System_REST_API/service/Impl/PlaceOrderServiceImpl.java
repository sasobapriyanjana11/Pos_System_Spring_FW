package lk.ijse.gdse68.POS_System_REST_API.service.Impl;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Service
@Transactional
public class PlaceOrderServiceImpl implements PlaceOrderService {

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
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderId(AppUtill.createOrderId());
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setTotal(orderDTO.getTotal());
        orderEntity.setDiscount(orderDTO.getDiscount());
        orderEntity.setSubTotal(orderDTO.getSubTotal());
        orderEntity.setCash(orderDTO.getCash());
        orderEntity.setBalance(orderDTO.getBalance());

        //save data to order entity
        OrderEntity save = orderdao.save(orderEntity);

        //save data to order details entity
        List<OrderDetailDTO> orderDetailsDTOS = orderDTO.getOrder_list();
        for (OrderDetailDTO orderDetailDTO : orderDetailsDTOS) {
            OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();

            orderDetailsEntity.setOd_id(AppUtill.createOrderDetailId());
            orderDetailsEntity.setQty(orderDetailDTO.getQty());
            orderDetailsEntity.setUnitPrice(orderDetailDTO.getUnit_price());

            // Fetch the ItemEntity
            ItemEntity itemEntity = itemDao.findById(orderDetailDTO.getItemCode())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid item code: " + orderDetailDTO.getItemCode()));

            orderDetailsEntity.setItem(itemEntity);  // Set the fetched ItemEntity
            orderDetailsEntity.setOrder(save);  // Set the saved OrderEntity

            orderDetailsdao.save(orderDetailsEntity);
        }

        if (save == null) {
            throw new DataPersistFailedException("Order save failed!");
        }
    }

    @Override
    public List<OrderDetailDTO> getOrderDetails() {
        return mapping.convertOrderDetailEntityListToOrderDetailDTOList(orderDetailsdao.findAll());
    }
}
