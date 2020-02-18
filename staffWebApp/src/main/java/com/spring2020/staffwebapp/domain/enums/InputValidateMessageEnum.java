package com.spring2020.staffwebapp.domain.enums;

public enum InputValidateMessageEnum
{
    ORDER_STATUS_NOT_FOUND("Order status is not found in database"),
    ORDER_NOT_FOUND("Order is not found in database"),
    ORDER_NOT_CANCELED("Order is not canceled"),
    CHANGE_ORDER_STT_COMPLETED_CANCELED("Cannot change status of completed or canceled orders"),
    PRODUCT_NOT_FOUND("Product is not found in database");

    private String message;

    InputValidateMessageEnum(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
