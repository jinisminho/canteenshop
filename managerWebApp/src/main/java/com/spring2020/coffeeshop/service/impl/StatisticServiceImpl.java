package com.spring2020.coffeeshop.service.impl;

import com.spring2020.coffeeshop.domain.dto.ProductStatisticDto;
import com.spring2020.coffeeshop.repository.StatisticRepository;
import com.spring2020.coffeeshop.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public List<ProductStatisticDto> showProductStatistic(LocalDate startDate, LocalDate endDate) {
        List<ProductStatisticDto> statisticDtos = new ArrayList<>();
        List<Object[]> result = statisticRepository.findSoldProductQuantity(startDate, endDate);
        result.forEach(objects ->
        {
            ProductStatisticDto dto = new ProductStatisticDto();
            dto.setQuantity(Integer.parseInt(objects[0].toString()));
            dto.setProductName((String) objects[1]);
            statisticDtos.add(dto);
        });
        return statisticDtos;
    }

    @Override
    public Double showRevenue(LocalDate startDate, LocalDate endDate) {
        return statisticRepository.findRevenue(startDate, endDate);
    }
}
