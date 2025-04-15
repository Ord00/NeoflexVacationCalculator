package vacation.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class VacationPayResponse {

    private BigDecimal amount;

}
