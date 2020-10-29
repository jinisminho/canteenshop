package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.UserInfoDto;
import com.spring2020.customerapp.domain.entity.AppUser;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T14:34:54+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
public class AppUserMapperImpl implements AppUserMapper {

    @Override
    public UserInfoDto toDto(AppUser entity) {
        if ( entity == null ) {
            return null;
        }

        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setFirstName( entity.getFirstName() );
        userInfoDto.setLastName( entity.getLastName() );
        userInfoDto.setPhone( entity.getPhone() );
        userInfoDto.setEmail( entity.getEmail() );
        userInfoDto.setGender( entity.getGender() );

        return userInfoDto;
    }
}
