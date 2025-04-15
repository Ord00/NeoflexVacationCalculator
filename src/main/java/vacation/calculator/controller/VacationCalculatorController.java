package vacation.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vacation.calculator.dto.VacationPayRequest;
import vacation.calculator.dto.VacationPayResponse;
import vacation.calculator.exceptions.VacationPayRequestException;
import vacation.calculator.service.VacationCalculatorService;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class VacationCalculatorController {

    private final VacationCalculatorService vacationCalculatorService;
    private final BigDecimal DEFAULT_VACATION_PAY = new BigDecimal(-1);

    public VacationCalculatorController(VacationCalculatorService vacationCalculatorService) {

        this.vacationCalculatorService = vacationCalculatorService;

    }

    @GetMapping
    public VacationPayResponse calculateVacationPay(BigDecimal avgSalary, int days, LocalDate startDate) {

        try {

            VacationPayRequest vacationPayRequest = new VacationPayRequest(avgSalary, days, startDate);
            return vacationCalculatorService.calculateVacationPay(vacationPayRequest);

        } catch (VacationPayRequestException e) {

            return new VacationPayResponse(DEFAULT_VACATION_PAY);

        }

    }

}
