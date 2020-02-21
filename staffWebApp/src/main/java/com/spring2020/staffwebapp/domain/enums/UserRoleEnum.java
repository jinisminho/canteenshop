package com.spring2020.staffwebapp.domain.enums;

public enum UserRoleEnum
{
    ROLE_CUSTOMER("2"),
    ROLE_STAFF("1"),
    ROLE_MANAGER("3");

    private String code;

    UserRoleEnum(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }
}
