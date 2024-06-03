package com.jins.jin.controller;

import com.jins.jin.entity.RandomValue;
import com.jins.jin.repository.RandomValueRepository;
import com.jins.jin.service.RandomValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RandomController {

    @Autowired
    private RandomValueService randomValueService;

    @Autowired
    private RandomValueRepository randomValueRepository;

    @GetMapping("/")
    public String showForm() {
        return "input";
    }

    @PostMapping("/random")
    public String submitForm(@RequestParam List<Integer> values) {
        values.forEach(value -> randomValueService.saveRandomValue(value));
        return "redirect:/random-values";
    }

    @GetMapping("/random-values")
    public ModelAndView showRandomValues() {
        List<RandomValue> randomValues = randomValueRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("random_values");
        modelAndView.addObject("randomValues", randomValues);
        return modelAndView;
    }
}
