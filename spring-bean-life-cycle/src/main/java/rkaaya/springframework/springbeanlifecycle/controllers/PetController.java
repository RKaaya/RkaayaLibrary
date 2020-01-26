package rkaaya.springframework.springbeanlifecycle.controllers;

import org.springframework.stereotype.Controller;
import rkaaya.springframework.springbeanlifecycle.services.PetService;

@Controller
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    public String say(){
        System.out.println("Say something!!! ");

        return petService.sayBark();
    }
}
