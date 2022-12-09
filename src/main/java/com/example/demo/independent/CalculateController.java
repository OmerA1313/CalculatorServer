package com.example.demo.independent;

import com.example.demo.CalculatorService;
import com.example.demo.Exceptions.ArgumentAmountException;
import com.example.demo.Exceptions.DivisioByZeroException;
import com.example.demo.Exceptions.OperationNotSupported;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ControllerAdvice
@RequestMapping("/independent")
public class CalculateController {

    @PostMapping(value = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalcResultDTO> calculate(@RequestBody CalculationBodyDTO body) {

            CalculatorService.tryValidateExpression(body.getArguments(), body.getOperation());
            Integer result = CalculatorService.execute(body.arguments, body.operation);
            return ResponseEntity.ok(new CalcResultDTO(result));
    }

    @ExceptionHandler(value = {ArgumentAmountException.class, DivisioByZeroException.class, OperationNotSupported.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    private ResponseEntity<CalcResultDTO> handleCalculationExceptions(ResponseStatusException i_ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new CalcResultDTO(i_ex.getReason()));
    }
}
