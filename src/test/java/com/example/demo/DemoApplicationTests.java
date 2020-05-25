package com.example.demo;

import com.example.demo.Service.ContractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    ContractService contractService;

    @Test
    void contextLoads ()
    {
    }


    @Test
    void searchTest () {
        System.out.println(contractService.findMotorhomeByPlate("CK33661"));
        System.out.println(contractService.findContractById(73));
        System.out.println(contractService.findCustomerById(114));
    }
}
