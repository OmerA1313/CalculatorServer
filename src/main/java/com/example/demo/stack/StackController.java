package com.example.demo.stack;

import com.example.demo.independent.CalcResultDTO;
import com.example.demo.independent.CalculationBodyDTO;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequestMapping("/stack")
public class StackController {

    @GetMapping("/size")
    public int getStackSize() {
        return StackManager.getStackSize();
    }

    @PostMapping("/arguments")
    public void addArguments(@RequestBody CalculationBodyDTO body) {
        StackManager.addArguments(body.getArguments());

    }

    @GetMapping("/operate")
    public ResponseEntity<CalcResultDTO> operate(@RequestParam String operation) {
        throw new NotImplementedException("operate endpoint not yet implemented");
    }

    @DeleteMapping("/arguments")
    public int deleteArguments(@RequestParam int count){
        return getStackSize();
    }
}
