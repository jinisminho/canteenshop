package com.spring2020.staffwebapp.domain.enums;

public enum ResponseErrorCodeEnum
{
    INPUT_FAIL("2");

    private String code;

    ResponseErrorCodeEnum(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return code;
    }
}
