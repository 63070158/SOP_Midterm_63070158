package com.example.sop_midterm_63070158;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorPriceController {
    @Autowired
    private CalculatorPriceService calc;
    @RequestMapping(value="/getPrice/{cost}/{profit}", method = RequestMethod.GET)
    public double serviceGetProducts(@PathVariable("cost") double cost,
                                     @PathVariable("profit") double profit) {
        return calc.getPrice(cost, profit);
    }
}
