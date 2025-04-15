package vacationCalculator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vacationCalculator.service.VacationCalculatorService;

@RestController
@RequestMapping("/calculate")
public class VacationCalculatorController {

    private final VacationCalculatorService vacationCalculatorService;

    public VacationCalculatorController(VacationCalculatorService vacationCalculatorService) {

        this.vacationCalculatorService = vacationCalculatorService;

    }

}
