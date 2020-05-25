package com.example.demo.Controller;

import com.example.demo.Model.Contract;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Motorhome;
import com.example.demo.Service.ContractService;
import com.example.demo.Service.CustomerService;
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
  CustomerService customerService;

  static Contract newContract = new Contract();

  @GetMapping("/contract/customerSelection")
  public String selectCustomers(Model model) {
    model.addAttribute("customers", customerService.fetchAll());
    return "home/contract/customerSelection";
  }

  @PostMapping("/contract/datesSelection")
  public String selectDates(@ModelAttribute Contract contract, Model model) {
    System.out.println("Dates: " + newContract);
    newContract.setCustomerId(contract.getCustomerId());
    return "home/contract/datesSelection";
  }

  @PostMapping("/contract/motorhomeSelection")
  public String selectMotorhome(@ModelAttribute Contract contract, Model model) {
    System.out.println("Motorhome: " + newContract);
    List<Motorhome> mhForRent = contractService.availableMotorhomes(contract.getStartDate(), contract.getEndDate());
    model.addAttribute("motorhomes", mhForRent);
    newContract.setStartDate(contract.getStartDate());
    newContract.setEndDate(contract.getEndDate());
    return "home/contract/motorhomeSelection";
  }

  @PostMapping("/contract/additionalsSelection")
  public String selectAdditionals(@ModelAttribute Contract contract, Model model) {
    System.out.println("Additionals: " + newContract);
    model.addAttribute("transfers", contractService.fetchAllTransfer());
    //We're not storing the extras anywhere? Consider making a addExtras method to the repo
    model.addAttribute("extras", contractService.fetchAllExtra());
    newContract.setLicencePlate(contract.getLicencePlate());
    return "home/contract/additionalsSelection";
  }

  @PostMapping("/contract/confirmContract")
  public String confirmDetails(@ModelAttribute Contract contract, Model model) {
    newContract.setPickId(contract.getPickId());
    newContract.setDropId(contract.getDropId());
    System.out.println("Confirm: " + newContract);
    //startKm in contract never changes value
    model.addAttribute("contract", newContract);
    model.addAttribute("customer", contractService.findCustomerById(newContract.getCustomerId()));
    model.addAttribute("motorhome", contractService.findMotorhomeByPlate(newContract.getLicencePlate()));
    return "home/contract/confirmContract";
  }

  @PostMapping("/contract/createContract")
  public String createContract() {
    System.out.println(newContract);
    contractService.addContract(newContract);
    newContract = new Contract();
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
