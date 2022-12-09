package com.example.demo.stack;

import com.example.demo.independent.CalcResultDTO;
import com.example.demo.independent.CalculationBodyDTO;
import com.example.demo.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/stack")
public class StackController {

    @GetMapping("/size")
    public int getStackSize() {
        return StackManager.getStackSize();
    }

    @PutMapping("/arguments")
    public void addArguments(@RequestBody CalculationBodyDTO body) {
        StackManager.addArguments(body.getArguments());
    }

    @GetMapping("/operate")
    public ResponseEntity<CalcResultDTO> operate(@RequestParam String operation) {
        List<Integer> arguments = StackManager.tryExtractArgumentsByOperation(operation);
        CalculatorService.tryValidateExpression(arguments, operation);
        int restult = CalculatorService.execute(arguments, operation);
        return ResponseEntity.ok(new CalcResultDTO(restult));
    }

    @DeleteMapping("/arguments")
    public int deleteArguments(@RequestParam int count) {
        return StackManager.deleteArguments(count);
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<CalcResultDTO> handleException(ResponseStatusException i_responseStatusException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new CalcResultDTO(i_responseStatusException.getReason()));
    }
}
