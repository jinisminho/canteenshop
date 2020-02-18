package com.spring2020.staffwebapp.domain.enums;

public enum DbStatusEnum
{
    SUCCESS("1"),
    PENDING("-1"),
    FAILED("0");

    private String code;

    DbStatusEnum(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }
}
