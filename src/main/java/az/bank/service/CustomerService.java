package az.bank.service;

import az.bank.dto.CustomerDto;
import az.bank.dto.converter.CustomerDtoConverter;
import az.bank.exception.CustomerNotFoundException;
import az.bank.model.Customer;
import az.bank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    public List<CustomerDto> getAllCustomer(){
        return customerRepository.findAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
    public CustomerDto getCustomerById(Long customerId){

        CustomerDto customerDto = new CustomerDto();
        return converter.convert(findCustomerById(customerId));
    }
    protected Customer findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found by id: " + id));
    }
}
