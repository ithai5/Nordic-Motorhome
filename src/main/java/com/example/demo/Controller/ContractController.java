package com.example.demo.Controller;

import com.example.demo.Model.Contract;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Motorhome;
import com.example.demo.Service.ContractService;
import com.example.demo.Service.IdHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Made by Thomas
@Controller
public class ContractController {
  @Autowired
  ContractService contractService;
  @Autowired
  IdHolderService idHolderService;

  @GetMapping("/contract/datesSelection")
  public String selectDates() {return "home/contract/datesSelection";}

  @PostMapping("/contract/motorhomeSelection")
  public String selectMotorhome(@ModelAttribute Contract contract, Model model) {
    List<Motorhome> mhForRent = contractService.availableCars(contract.getStartDate(), contract.getEndDate());
    System.out.println(mhForRent);
    model.addAttribute("motorhomes", mhForRent);
    return "home/contract/motorhomeSelection";
  }

  @GetMapping("/contract/viewContract/{contractId}")
  public String viewContract(@PathVariable("contractId") int contractId, Model model) {
    Contract contract = idHolderService.findContractById(contractId);
    model.addAttribute("contract", contract);
    model.addAttribute("customer", idHolderService.findCustomerById(contract.getCustomerId()));
    model.addAttribute("motorhome", idHolderService.findMotorhomeByPlate(contract.getLicencePlate()));
    return "home/contract/viewContract";
  }

  @GetMapping("/contract")
  public String menu(Model model) {
    model.addAttribute("contracts", contractService.fetchAll());
    return "home/contract/contractMenu";
  }

  //CONSIDER ADDING A CONFIRMATION BEFORE DELETE
  @GetMapping("/contract/deleteContract/{contractId}")
  public String deleteContract(@PathVariable("contractId") int contractId){
    contractService.deleteContract(contractId);
    return "redirect:/contract";
  }

  @PostMapping("/contract/searchContract")
  public String searchContract(@ModelAttribute Contract keyword, Model model){

    List<Contract> searchHits = contractService.searchContract(keyword.getStartDate());
    if (searchHits.isEmpty()){ //check it there is any results for the search and direct to another page
      return "home/contract/contractMenu";
    }
    model.addAttribute("contracts", searchHits); //show the result of the search statement
    return "home/contract/searchContract";
  }

}
