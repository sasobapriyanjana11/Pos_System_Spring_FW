package lk.ijse.gdse68.POS_System_REST_API.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.POS_System_REST_API.customObj.CustomerErrorResponse;
import lk.ijse.gdse68.POS_System_REST_API.customObj.CustomerResponse;
import lk.ijse.gdse68.POS_System_REST_API.dao.Customerdao;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.CustomerDTO;
import lk.ijse.gdse68.POS_System_REST_API.entity.CustomerEntity;
import lk.ijse.gdse68.POS_System_REST_API.exception.CustomerNotFoundException;
import lk.ijse.gdse68.POS_System_REST_API.exception.DataPersistFailedException;
import lk.ijse.gdse68.POS_System_REST_API.service.CustomerService;
import lk.ijse.gdse68.POS_System_REST_API.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private Customerdao customerdao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity save = customerdao.save(mapping.convertCustomerDTOToCustomerEntity(customerDTO));
        if (save == null){
            throw new DataPersistFailedException("cannot save customer");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.convertCustomerEntityListToCustomerDTOList(customerdao.findAll());
    }

    @Override
    public CustomerResponse getSelectedCustomer(String id) {
        if (customerdao.existsById(id)) {
            return mapping.convertCustomerEntityToCustomerDTO(customerdao.getReferenceById(id));
        }else {
            return new CustomerErrorResponse(0,"customer not found");
        }
    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {
        Optional<CustomerEntity>tmp=customerdao.findById(id);
        if(!tmp.isPresent()){
            throw new CustomerNotFoundException("Customer not found !");
        }else{
            tmp.get().setName(customerDTO.getName());
            tmp.get().setAddress(customerDTO.getAddress());
            tmp.get().setSalary(customerDTO.getSalary());

        }

    }

    @Override
    public void deleteCustomer(String id) {
        Optional<CustomerEntity> tmp = customerdao.findById(id);

        if (!tmp.isPresent()) {
            throw new CustomerNotFoundException("Customer not found !");
        }else {
            customerdao.deleteById(id);
        }
    }
}
