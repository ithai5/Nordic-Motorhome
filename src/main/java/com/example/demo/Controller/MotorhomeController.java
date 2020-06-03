package com.example.demo.Controller;

import com.example.demo.Model.Motorhome;
import com.example.demo.Service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Made by Ignacio

//In the MVC pattern, the Controller package serves as a connection point between the View (UI) and the Model (data)

//This annotation informs the Spring framework that this class is a Controller class
@Controller
public class MotorhomeController {
    //This annotation is used to connect the MotorhomeController class with the MotorhomeService class
    @Autowired
    MotorhomeService motorhomeService;

    //Previous Get method
    //VIEW ALL
    @PostMapping("/motorhome/viewMotorhome")   //This annotation uses the method POST to send a request to the server
    public String viewMotorhomes (Model model){
        List<Motorhome> motorhomeList = motorhomeService.fetchAll(); //An object of the class MotorhomeService is used to call the method fetchAll
        model.addAttribute("motorhomes", motorhomeList);  //We use the method addAtribute from the model to display the data in the view (HTML)
        return "home/motorhome/viewMotorhome";
    }

    //Previous Get method
    //ADD
    @PostMapping("/motorhome/createMotorhome")
    public String createMotorhome(){
        return "home/motorhome/createMotorhome";
    }

    @PostMapping("/motorhome/addMotorhome")
    public String addMotorhome (@ModelAttribute Motorhome motorhome){   //This annotation converts the data from the view (HTML) into a model element
        motorhomeService.addMotorhome(motorhome);
        return "home/motorhome/actionSuccessful";
    }

    //Previous Get method
    //SEARCH
    @PostMapping("/motorhome/searchMotorhome")
    public String searchMotorhome (@ModelAttribute Motorhome motorhome, Model model){
        List<Motorhome> motorhomeList = motorhomeService.searchMotorhome(motorhome.getLicencePlate());
        if(motorhomeList.isEmpty()||motorhomeList==null){ //checks if there is any results for the search and direct to another page
            return "home/motorhome/noSearchResultsMh";
        }
        model.addAttribute("motorhomes", motorhomeList); //shows the result of the search statement
        return "home/motorhome/searchMotorhome";
    }

    //Previous Get method
    //DELETE
    @PostMapping("/motorhome/deleteMotorhome/{licencePlate}")
    public String deleteMotorhome(@PathVariable("licencePlate") String licencePlate){
        Boolean delete = motorhomeService.deleteMotorhome(licencePlate);
        return "home/motorhome/actionSuccessful";
    }

    //Previous Get method
    //UPDATE
    @PostMapping("/motorhome/updateMotorhome/{licencePlate}")
    public String updateMotorhome(@PathVariable("licencePlate") String licencePlate, Model model){  //This annotation is used to access an specific part of the URL
        model.addAttribute("motorhome", motorhomeService.findMotorhomeByPlate(licencePlate));
        return "home/motorhome/updateMotorhome";
    }

    //Previous Get method
    @PostMapping("/motorhome/updateMotorhome")
    public String updateMotorhome(@ModelAttribute Motorhome motorhome){
        motorhomeService.updateMotorhome(motorhome);
        return "home/motorhome/actionSuccessful";
    }
}
