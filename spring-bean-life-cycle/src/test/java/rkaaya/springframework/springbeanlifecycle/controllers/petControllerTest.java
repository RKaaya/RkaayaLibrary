package rkaaya.springframework.springbeanlifecycle.controllers;

import org.junit.Before;
import org.junit.Test;
import rkaaya.springframework.springbeanlifecycle.services.PetServiceImpl;

import static org.junit.Assert.assertEquals;

public class petControllerTest {
    private PetController petController;

    @Before
    public void setUp() throws Exception {
        this.petController = new PetController(new PetServiceImpl());
    }

    @Test
    public void testSay() throws Exception {
        assertEquals("Bark", petController.say());
    }
}
