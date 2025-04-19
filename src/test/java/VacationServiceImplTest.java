import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import vacation.calculator.dto.VacationPayRequest;
import vacation.calculator.dto.VacationPayResponse;
import vacation.calculator.exceptions.VacationPayRequestException;
import vacation.calculator.service.VacationCalculatorServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VacationServiceImplTest {

    private static final BigDecimal AVG_DAYS_IN_MONTH = new BigDecimal("29.3");

    @InjectMocks
    private VacationCalculatorServiceImpl vacationPayService;

    @Test
    void testPayExcludingWeekends() {

        int days = 14;

        VacationPayRequest request = new VacationPayRequest(
                new BigDecimal(30000),
                days,
                LocalDate.of(2024, 10, 10));

        VacationPayResponse response = vacationPayService.calculateVacationPay(request);

        assertEquals(BigDecimal.valueOf(30000).multiply(BigDecimal.valueOf(days - 4))
                .divide(AVG_DAYS_IN_MONTH,
                        2,
                        RoundingMode.HALF_UP), response.getAmount());
    }

    @Test
    void testPayCalculationExcludingAllNewYearsHolidays() {

        int days = 10;

        VacationPayRequest request = new VacationPayRequest(
                new BigDecimal(30000),
                days,
                LocalDate.of(2023, 1, 1));

        VacationPayResponse response = vacationPayService.calculateVacationPay(request);

        assertEquals(
                BigDecimal.valueOf(30000).multiply(BigDecimal.valueOf(days - 8))
                        .divide(AVG_DAYS_IN_MONTH,
                                2,
                                RoundingMode.HALF_UP),
                response.getAmount());

    }

    @Test
    void testPayCalculationWithNegativeSalary() {
        
        VacationPayRequest request = new VacationPayRequest();
        request.setAvgSalary(new BigDecimal(-100));
        request.setDays(10);

        assertThrows(VacationPayRequestException.class, 
                () -> vacationPayService.calculateVacationPay(request));
    }

    @Test
    void testPayCalculationWithNegativeDays() {

        VacationPayRequest request = new VacationPayRequest();
        request.setAvgSalary(new BigDecimal(100));
        request.setDays(-10);

        assertThrows(VacationPayRequestException.class,
                () -> vacationPayService.calculateVacationPay(request));
    }

}
