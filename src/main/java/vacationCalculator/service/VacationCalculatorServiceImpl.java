package vacationCalculator.service;

import vacationCalculator.dto.VacationPayRequest;
import vacationCalculator.dto.VacationPayResponse;
import org.springframework.stereotype.Service;

@Service
public class VacationCalculatorServiceImpl implements VacationCalculatorService {

    @Override
    public VacationPayResponse calculateVacationPay(VacationPayRequest vacationPayRequest) {

        return new VacationPayResponse();

    }

}
