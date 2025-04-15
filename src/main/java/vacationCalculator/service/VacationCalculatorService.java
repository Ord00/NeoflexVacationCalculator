package vacationCalculator.service;

import vacationCalculator.dto.VacationPayRequest;
import vacationCalculator.dto.VacationPayResponse;

public interface VacationCalculatorService {

    VacationPayResponse calculateVacationPay(VacationPayRequest vacationPayRequest);

}
