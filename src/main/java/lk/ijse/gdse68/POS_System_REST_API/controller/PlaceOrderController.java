package lk.ijse.gdse68.POS_System_REST_API.controller;

import lk.ijse.gdse68.POS_System_REST_API.dto.impl.OrderDTO;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.OrderDetailDTO;
import lk.ijse.gdse68.POS_System_REST_API.exception.DataPersistFailedException;
import lk.ijse.gdse68.POS_System_REST_API.service.PlaceOrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class PlaceOrderController {

    @Autowired
    private final PlaceOrderService placeOrderService;

    private static final Logger logger = LoggerFactory.getLogger(PlaceOrderController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> placeOrder(@RequestBody OrderDTO orderDTO) {
        if (orderDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                placeOrderService.saveOrder(orderDTO);
                logger.info("Order placed successfully: {}", orderDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                logger.error("Failed to persist order data: {}", orderDTO, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                logger.error("Unexpected error occurred while saving order: {}", orderDTO, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailDTO> getOrderDetails() {
        return placeOrderService.getOrderDetails();
    }

}
