package com.spring2020.customerapp.domain.enums;

import com.spring2020.customerapp.domain.entity.AppRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public enum AppRoleEnum {
    ROLE_STAFF(1, "ROLE_STAFF"),
    ROLE_CUSTOMER(2, "ROLE_CUSTOMER"),
    ROLE_MANAGER(3, "ROLE_MANAGER");

    private AppRole appRole;

    AppRoleEnum(int id, String name) {
        appRole = new AppRole();
        this.appRole.setId(id);
        this.appRole.setName(RoleNameEnum.valueOf(name));
    }

    public AppRole getAppRole() {
        return appRole;
    }
}
