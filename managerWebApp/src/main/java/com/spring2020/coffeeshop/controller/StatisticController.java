package com.spring2020.coffeeshop.controller;

import com.spring2020.coffeeshop.domain.dto.ComplexStatisticDto;
import com.spring2020.coffeeshop.domain.dto.ProductStatisticDto;
import com.spring2020.coffeeshop.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.spring2020.coffeeshop.util.ConstantUtil.DATE_PATTERN;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping
    public ResponseEntity<Object> showStatistic(@DateTimeFormat(pattern = DATE_PATTERN)
                                                @RequestParam(value = "startDate") LocalDate startDate,
                                                @DateTimeFormat(pattern = DATE_PATTERN)
                                                @RequestParam(value = "endDate") LocalDate endDate) {
        List<ProductStatisticDto> productStatisticDtoList = statisticService.showProductStatistic(startDate, endDate);
        Double revenue = statisticService.showRevenue(startDate, endDate);
        return ResponseEntity.ok(new ComplexStatisticDto(productStatisticDtoList, revenue));
    }
}
