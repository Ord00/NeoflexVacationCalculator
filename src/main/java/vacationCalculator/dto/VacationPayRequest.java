package vacationCalculator.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VacationPayRequest {

    private BigDecimal avgSalary;
    private int days;
    private LocalDate startDate;

}
