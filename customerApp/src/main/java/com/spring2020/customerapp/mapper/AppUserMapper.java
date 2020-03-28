package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.UserInfoDto;
import com.spring2020.customerapp.domain.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE = Mappers.getMapper( AppUserMapper.class );

    UserInfoDto toDto(AppUser entity);
}
