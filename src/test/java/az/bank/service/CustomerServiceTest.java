package az.bank.service;

import az.bank.dto.CustomerDto;
import az.bank.dto.converter.CustomerDtoConverter;
import az.bank.exception.CustomerNotFoundException;
import az.bank.model.Customer;
import az.bank.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest  {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;

    @BeforeEach
    public void setUp(){
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerDtoConverter = Mockito.mock(CustomerDtoConverter.class);
        customerService = new CustomerService(customerRepository,customerDtoConverter);
    }
    @Test
    public void testFindByCustomerId_whenCustomerIdExist_shouldReturnCustomer(){
        Customer customer = new Customer(1L,"name","surname", List.of());

        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.findCustomerById(1L);

        assertEquals(customer,result);
    }
    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,()-> customerService.findCustomerById(1L));
    }
    @Test
    public void testGetCustomerById_whenCustomerIdExist_shouldReturnCustomer(){
        Customer customer = new Customer(1L,"name","surname", List.of());

        CustomerDto customerDto = new CustomerDto(1L,"name","surname",List.of());

        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Mockito.when(customerDtoConverter.convert(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getCustomerById(1L);

        assertEquals(customerDto,result);
    }
    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                ()-> customerService.getCustomerById(1L));

        Mockito.verifyNoInteractions(customerDtoConverter);

    }
}