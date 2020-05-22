package com.example.demo.Controller;

import com.example.demo.Model.Motorhome;
import com.example.demo.Service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class MotorhomeController {
    @Autowired
    MotorhomeService motorhomeService;

}
