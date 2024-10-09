package lk.ijse.gdse68.POS_System_REST_API.service;

import lk.ijse.gdse68.POS_System_REST_API.customObj.CustomerResponse;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerResponse getSelectedCustomer(String id);

    void updateCustomer(String id, CustomerDTO customerDTO);

    void deleteCustomer(String id);
}
