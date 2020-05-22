package com.example.demo;

import com.example.demo.Model.Customer;
import com.example.demo.Service.IdHolderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    IdHolderService idHolderService;

    @Test
    void contextLoads ()
    {
    }


    @Test
    void searchTest () {
        System.out.println(idHolderService.searchMotorhomeByPlate("CK33661"));
    }
}
