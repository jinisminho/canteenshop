package com.spring2020.coffeeshop.repository;

import java.time.LocalDate;
import java.util.List;

public interface StatisticRepository {

    List<Object[]> findSoldProductQuantity(LocalDate startDate, LocalDate endDate);

    Double findRevenue(LocalDate startDate, LocalDate endDate);

}
