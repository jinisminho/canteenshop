package com.spring2020.staffwebapp.domain.enums;

public enum ResponseErrorMessageEnum
{
    INPUT_FAIL("Input invalid");

    private String message;

    ResponseErrorMessageEnum(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
