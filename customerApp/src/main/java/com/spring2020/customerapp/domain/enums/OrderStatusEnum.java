package com.spring2020.customerapp.domain.enums;

public enum OrderStatusEnum
{
    Pending(1),
    Confirmed(2),
    Delivering(3),
    Completed(4),
    Canceled(5);

    private int id;

    OrderStatusEnum(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
}
