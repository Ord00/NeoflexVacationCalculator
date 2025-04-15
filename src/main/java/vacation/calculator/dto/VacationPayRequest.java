package vacation.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class VacationPayRequest {

    private BigDecimal avgSalary;
    private int days;
    private LocalDate startDate;

}
