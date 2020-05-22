package com.example.demo.Controller;

import com.example.demo.Model.Contract;
import com.example.demo.Model.Motorhome;
import com.example.demo.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContractController {
  @Autowired
  ContractService contractService;

  @GetMapping("/contract/datesSelection")
  public String selectDates() {return "home/contract/datesSelection";}

  @PostMapping("/contract/motorhomeSelection")
  public String selectMotorhome(@ModelAttribute Contract contract, Model model) {
    List<Motorhome> mhForRent = contractService.availableCars(contract.getStartDate(), contract.getEndDate());
    System.out.println(mhForRent);
    model.addAttribute("motorhomes", mhForRent);
    return "home/contract/motorhomeSelection";
  }

}
