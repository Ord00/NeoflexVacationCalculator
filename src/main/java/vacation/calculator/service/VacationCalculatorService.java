package vacation.calculator.service;

import vacation.calculator.dto.VacationPayRequest;
import vacation.calculator.dto.VacationPayResponse;

public interface VacationCalculatorService {

    VacationPayResponse calculateVacationPay(VacationPayRequest vacationPayRequest);

}
