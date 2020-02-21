package com.spring2020.coffeeshop.service;

import com.spring2020.coffeeshop.domain.dto.ProductStatisticDto;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService {

    List<ProductStatisticDto> showProductStatistic(LocalDate startDate, LocalDate ennDate);

    Double showRevenue(LocalDate startDate, LocalDate ennDate);

}
