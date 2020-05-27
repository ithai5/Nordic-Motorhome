package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.ContractService;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

//Made by Thomas
@Controller
public class ContractController {
  @Autowired
  ContractService contractService;

  @Autowired
  CustomerService customerService;

  @GetMapping("/contract/customerSelection")
  public String selectCustomers(Model model) {
    model.addAttribute("customers", customerService.fetchAll());
    return "home/contract/customerSelection";
  }

  @PostMapping("/contract/datesSelection")
  public String selectDates(@ModelAttribute Contract contract, Model model) {
    model.addAttribute("contract", contract);
    return "home/contract/datesSelection";
  }

  @PostMapping("/contract/motorhomeSelection")
  public String selectMotorhome(@ModelAttribute Contract contract, Model model) {
    if (!contract.validDuration()) {
      return "home/contract/datesIncorrect";
    }
    List<Motorhome> mhForRent = contractService.availableMotorhomes(contract.getStartDate(), contract.getEndDate());
    model.addAttribute("motorhomes", mhForRent);

    model.addAttribute("contract", contract);
    return "home/contract/motorhomeSelection";
  }

  @PostMapping("/contract/additionalsSelection")
  public String selectAdditionals(@ModelAttribute Contract contract, Model model) {
    //Set the startKm value with the odometer from the car chosen
    contract.setStartKm(contractService.findMotorhomeByPlate(contract.getLicencePlate()).getOdometer());

    ExtraWrapper extras = new ExtraWrapper(new ArrayList<Extra>(contractService.fetchAllExtra()));

    model.addAttribute("extrasD", extras.getExtras());
    model.addAttribute("extras", extras);
    model.addAttribute("transfers", contractService.fetchAllTransfer());

    model.addAttribute("contract", contract);
    return "home/contract/additionalsSelection";
  }

  @PostMapping("/contract/confirmContract")
  public String confirmDetails(@ModelAttribute Contract contract, @ModelAttribute ExtraWrapper extras, Model model) {
    model.addAttribute("contract", contract);
    model.addAttribute("customer", contractService.findCustomerById(contract.getCustomerId()));
    model.addAttribute("motorhome", contractService.findMotorhomeByPlate(contract.getLicencePlate()));

    contractService.addContract(contract);
    contractService.addExtrasToContract(extras.getExtras());
    return "home/contract/confirmContract";
  }

  @PostMapping("/contract/cancelContract")
  public String cancelContract() {
    contractService.deleteExtrasFromLastContract();
    contractService.deleteLastContract();
    return "redirect:/contract";
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
    model.addAttribute("contracts", contractService.fetchAllContract());
    return "home/contract/contractMenu";
  }

  //CONSIDER ADDING A CONFIRMATION BEFORE DELETE
  @GetMapping("/contract/deleteContract/{contractId}")
  public String deleteContract(@PathVariable("contractId") int contractId){
    contractService.deleteExtrasFromContract(contractId);
    contractService.deleteContract(contractId);
    return "redirect:/contract";
  }

  @PostMapping("/contract/searchContract")
  public String searchContract(@ModelAttribute Contract keyword, Model model){

    List<Contract> searchHits = contractService.searchContract(keyword.getStartDate());
    if(searchHits ==null){
      return "index1";
    }
    if (searchHits.isEmpty()){ //check it there is any results for the search and direct to another page
      return "home/contract/contractMenu";
    }
    model.addAttribute("contracts", searchHits); //show the result of the search statement
    return "home/contract/searchContract";
  }

}
