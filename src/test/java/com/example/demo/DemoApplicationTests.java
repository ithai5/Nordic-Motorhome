package com.example.demo;

import com.example.demo.Service.ContractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.util.Date;

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

    @Test
    void lastAddedTest() {
        System.out.println("Results from contract table:");
        contractService.lastAddedToTable("Contract");
        System.out.println("Results from MhSpecs table:");
        contractService.lastAddedToTable("MhSpecs");
        System.out.println("Results from Transfer table:");
        contractService.lastAddedToTable("Transfer");
        contractService.lastAddedToTable("Customer");
    }

    @Test
    void dateFormatTest() {
        System.out.println(contractService.fetchAllContract().get(0).getStartDate());
    }

    @Test
    void currentDate(){
        Date current = new Date();
        System.out.println(DateFormat.getInstance().format(current));
    }
}
