package lk.ijse.gdse68.POS_System_REST_API.controller;

import lk.ijse.gdse68.POS_System_REST_API.customObj.ItemResponse;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.ItemDTO;
import lk.ijse.gdse68.POS_System_REST_API.exception.DataPersistFailedException;
import lk.ijse.gdse68.POS_System_REST_API.exception.ItemNotFoundException;
import lk.ijse.gdse68.POS_System_REST_API.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    @Autowired
    private final ItemService itemService;

    private static final Logger logger = LoggerFactory.getLogger(AbstractReadWriteAccess.Item.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addItem(@RequestBody ItemDTO itemDTO) {
        if (itemDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                itemService.saveItem(itemDTO);
                logger.info("Item saved successfully: {}", itemDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                logger.error("Failed to persist item data: {}", itemDTO, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                logger.error("Unexpected error occurred while saving item: {}", itemDTO, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO>getAllItems(){
        return itemService.getAllItems();
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemResponse getItemById(@PathVariable("id") String id,@RequestBody ItemDTO itemDTO){
        return itemService.getItemById(id);
    }

    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@PathVariable("id") String id,@RequestBody ItemDTO itemDTO){
        try{
            if(itemDTO==null&&(id==null||itemDTO.equals(""))){
                logger.warn("Received null CustomerDTO");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.updateItem(id,itemDTO);
            logger.info("Item updated successfully: {}", itemDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e){
            logger.error("Item not found: ID {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            logger.error("Unexpected error occurred while updating item: {}", itemDTO, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") String id) {
        try {
            itemService.deleteItem(id);
            logger.info("Item deleted successfully: ID {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e){
            logger.error("Item not found for deletion: ID {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Unexpected error occurred while deleting item: ID {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
