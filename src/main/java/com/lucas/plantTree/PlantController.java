package com.lucas.plantTree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planters")
public class PlantController {
    @Autowired
    private PlanterDAO planterDAO;

    @GetMapping
    public List<Planter> getAllPlanters() {
        return planterDAO.getAllPlanters();
    }

    @PostMapping
    public void createPlanter(@RequestBody Planter planter) {
        planterDAO.insertPlanter(planter);
    }
}

