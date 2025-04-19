package vacation.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VacationPayRequest {

    private BigDecimal avgSalary;
    private int days;
    private LocalDate startDate;

}
