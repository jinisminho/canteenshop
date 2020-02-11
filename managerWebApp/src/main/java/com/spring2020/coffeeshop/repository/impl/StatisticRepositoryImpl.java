package com.spring2020.coffeeshop.repository.impl;

import com.spring2020.coffeeshop.repository.StatisticRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class StatisticRepositoryImpl implements StatisticRepository {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> findSoldProductQuantity(LocalDate startDate, LocalDate endDate) {
        Query query = entityManager.createNativeQuery(
                "select count(od.quantity) as quantity, p.name as product_name\n" +
                        "from customer_order co\n" +
                        "join order_detail od on co.id = od.order_id\n" +
                        "join product p  on p.id = od.product_id\n" +
                        "where co.create_at  between ? and ? + interval 1 day\n" +
                        "group by p.name");
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        return (List<Object[]>) query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Double findRevenue(LocalDate startDate, LocalDate endDate) {
        Query query = entityManager.createNativeQuery(
                "select sum(total_price) as revenue\n" +
                        "from customer_order co\n" +
                        "where  co.create_at  between ? and ? + interval 1 day");
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        List<Object> result = (List<Object>) query.getResultList();
        if (result.get(0) == null) {
            return 0D;
        }
        return (Double) result.get(0);
    }
}
