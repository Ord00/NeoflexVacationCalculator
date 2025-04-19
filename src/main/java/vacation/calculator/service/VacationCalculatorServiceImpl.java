package vacation.calculator.service;

import vacation.calculator.dto.VacationPayRequest;
import vacation.calculator.dto.VacationPayResponse;
import org.springframework.stereotype.Service;
import vacation.calculator.exceptions.VacationPayRequestException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static vacation.calculator.utils.HolidaysHandler.isHoliday;
import static vacation.calculator.utils.LoggerInitializer.initializeLogger;

@Service
public class VacationCalculatorServiceImpl implements VacationCalculatorService {

    private static final Logger logger = initializeLogger(
            "src/main/java/vacation/calculator/logs/vacation-calculator-service-logs.log",
            VacationCalculatorServiceImpl.class.getName());

    private static final BigDecimal AVG_DAYS_IN_MONTH = new BigDecimal("29.3");

    @Override
    public VacationPayResponse calculateVacationPay(VacationPayRequest vacationPayRequest) {

        logger.log(Level.INFO,
                "Started calculation of vacation pay for request with parameters: {0}",
                vacationPayRequest.toString());

        checkRequest(vacationPayRequest);

        BigDecimal vacationPay = calculatePayValue(

                calculateWorkingDays(
                        vacationPayRequest.getDays(),
                        vacationPayRequest.getStartDate()
                ),
                vacationPayRequest.getAvgSalary()

        );

        logger.log(Level.INFO,
                "Ended successfully calculation of vacation pay for request with parameters: {0}",
                vacationPayRequest.toString());

        return new VacationPayResponse(vacationPay);

    }

    private void checkRequest(VacationPayRequest vacationPayRequest) {

        BigDecimal avgSalary = vacationPayRequest.getAvgSalary();
        String avgSalaryErrorMsg = String.format("Average salary is %s, but must be positive value!", avgSalary);

        if (avgSalary == null || avgSalary.compareTo(BigDecimal.ZERO) <= 0) {

            logger.log(Level.SEVERE, avgSalaryErrorMsg);
            throw new VacationPayRequestException(avgSalaryErrorMsg);

        }

        int days = vacationPayRequest.getDays();
        String daysErrorMsg = String.format("Number of days is %s, but must be positive value!", days);

        if (days <= 0) {

            logger.log(Level.SEVERE, daysErrorMsg);
            throw new VacationPayRequestException(daysErrorMsg);

        }

    }

    private int calculateWorkingDays(int days, LocalDate startDate) {

        int workingDays = days;

        int checkedDays = 0;
        LocalDate date = startDate;

        while (checkedDays < days) {

            if (isWeekend(date) || isHoliday(date)) {

                --workingDays;

            }

            ++checkedDays;
            date = date.plusDays(1);

        }

        return workingDays;

    }

    private boolean isWeekend(LocalDate date) {

        return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;

    }

    private BigDecimal calculatePayValue(int days, BigDecimal avgSalary) {

        return avgSalary.multiply(BigDecimal.valueOf(days))
                .divide(AVG_DAYS_IN_MONTH,
                        2,
                        RoundingMode.HALF_UP);

    }

}
