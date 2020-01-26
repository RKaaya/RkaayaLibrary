package rkaaya.springframework.springbeanlifecycle.services;

import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    @Override
    public String sayBark() {
        return "Bark";
    }
}
