package com.example.demo.Controller;

import com.example.demo.Model.Contract;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Extra;
import com.example.demo.Model.Motorhome;
import com.example.demo.Service.ContractService;
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

  @GetMapping("/contract/datesSelection")
  public String selectDates() {return "home/contract/datesSelection";}

  @PostMapping("/contract/motorhomeSelection")
  public String selectMotorhome(@ModelAttribute Contract contract, Model model) {
    List<Motorhome> mhForRent = contractService.availableMotorhomes(contract.getStartDate(), contract.getEndDate());
    model.addAttribute("motorhomes", mhForRent);
    return "home/contract/motorhomeSelection";
  }

  @GetMapping("/contract/viewContract/{contractId}")
  public String viewContract(@PathVariable("contractId") int contractId, Model model) {
    Contract contract = contractService.findContractById(contractId);
    model.addAttribute("contract", contract);
    model.addAttribute("customer", contractService.findCustomerById(contract.getCustomerId()));
    model.addAttribute("motorhome", contractService.findMotorhomeByPlate(contract.getLicencePlate()));
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

  @GetMapping("/contract/additionalsSelection")
  public String selectAdditionals(@ModelAttribute Contract contract, Model model) {
    model.addAttribute("transfers", contractService.fetchAllTransfer());
    return "home/contract/chooseAdditionals";
  }

  @GetMapping("/contract/extraToContract")
  public String viewExtra(Model model){
    List<Extra> extraList = contractService.fetchAllExtra();
    model.addAttribute("extras", extraList);
    return "home/contract/extraToContract";
  }

}
