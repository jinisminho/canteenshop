package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.AppRoleDto;
import com.spring2020.customerapp.domain.dto.AppUserDto;
import com.spring2020.customerapp.domain.dto.CreateAppUserDto;
import com.spring2020.customerapp.domain.dto.CreateCustomerDto;
import com.spring2020.customerapp.domain.dto.CustomerDto;
import com.spring2020.customerapp.domain.dto.FullAppUserDto;
import com.spring2020.customerapp.domain.dto.UpdateAppUserDto;
import com.spring2020.customerapp.domain.dto.UpdateCustomerDto;
import com.spring2020.customerapp.domain.entity.AppRole;
import com.spring2020.customerapp.domain.entity.AppUser;
import com.spring2020.customerapp.domain.entity.Customer;
import com.spring2020.customerapp.domain.enums.RoleNameEnum;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T14:34:54+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDto customer) {
        if ( customer == null ) {
            return null;
        }

        Customer customer1 = new Customer();

        customer1.setId( customer.getId() );
        customer1.setAppUser( appUserDtoToAppUser( customer.getAppUser() ) );

        return customer1;
    }

    @Override
    public Customer toEntity(CreateCustomerDto customer) {
        if ( customer == null ) {
            return null;
        }

        Customer customer1 = new Customer();

        customer1.setAppUser( createAppUserDtoToAppUser( customer.getAppUser() ) );

        return customer1;
    }

    @Override
    public Customer toEntity(UpdateCustomerDto customer) {
        if ( customer == null ) {
            return null;
        }

        Customer customer1 = new Customer();

        customer1.setId( customer.getId() );
        customer1.setAppUser( fullAppUserDtoToAppUser( customer.getAppUser() ) );

        return customer1;
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setAppUser( appUserToAppUserDto( customer.getAppUser() ) );

        return customerDto;
    }

    @Override
    public AppUserDto toAppUserDto(UpdateAppUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        AppUserDto appUserDto = new AppUserDto();

        appUserDto.setFirstName( dto.getFirstName() );
        appUserDto.setLastName( dto.getLastName() );
        appUserDto.setPhone( dto.getPhone() );
        appUserDto.setEmail( dto.getEmail() );

        return appUserDto;
    }

    @Override
    public UpdateCustomerDto toUpdateDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        UpdateCustomerDto updateCustomerDto = new UpdateCustomerDto();

        updateCustomerDto.setId( customer.getId() );
        updateCustomerDto.setAppUser( appUserToFullAppUserDto( customer.getAppUser() ) );

        return updateCustomerDto;
    }

    @Override
    public CreateAppUserDto toCrAppUserDto(CreateAppUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        CreateAppUserDto createAppUserDto = new CreateAppUserDto();

        createAppUserDto.setUsername( dto.getUsername() );
        createAppUserDto.setPassword( dto.getPassword() );
        createAppUserDto.setFirstName( dto.getFirstName() );
        createAppUserDto.setLastName( dto.getLastName() );
        createAppUserDto.setPhone( dto.getPhone() );
        createAppUserDto.setEmail( dto.getEmail() );
        createAppUserDto.setGender( dto.getGender() );

        return createAppUserDto;
    }

    protected AppUser appUserDtoToAppUser(AppUserDto appUserDto) {
        if ( appUserDto == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setId( appUserDto.getId() );
        appUser.setUsername( appUserDto.getUsername() );
        appUser.setFirstName( appUserDto.getFirstName() );
        appUser.setLastName( appUserDto.getLastName() );
        appUser.setPhone( appUserDto.getPhone() );
        appUser.setEmail( appUserDto.getEmail() );
        appUser.setGender( appUserDto.getGender() );
        appUser.setActive( appUserDto.isActive() );

        return appUser;
    }

    protected AppUser createAppUserDtoToAppUser(CreateAppUserDto createAppUserDto) {
        if ( createAppUserDto == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setUsername( createAppUserDto.getUsername() );
        appUser.setPassword( createAppUserDto.getPassword() );
        appUser.setFirstName( createAppUserDto.getFirstName() );
        appUser.setLastName( createAppUserDto.getLastName() );
        appUser.setPhone( createAppUserDto.getPhone() );
        appUser.setEmail( createAppUserDto.getEmail() );
        appUser.setGender( createAppUserDto.getGender() );

        return appUser;
    }

    protected AppRole appRoleDtoToAppRole(AppRoleDto appRoleDto) {
        if ( appRoleDto == null ) {
            return null;
        }

        AppRole appRole = new AppRole();

        appRole.setId( appRoleDto.getId() );
        if ( appRoleDto.getName() != null ) {
            appRole.setName( Enum.valueOf( RoleNameEnum.class, appRoleDto.getName() ) );
        }

        return appRole;
    }

    protected AppUser fullAppUserDtoToAppUser(FullAppUserDto fullAppUserDto) {
        if ( fullAppUserDto == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setId( fullAppUserDto.getId() );
        appUser.setUsername( fullAppUserDto.getUsername() );
        appUser.setPassword( fullAppUserDto.getPassword() );
        appUser.setFirstName( fullAppUserDto.getFirstName() );
        appUser.setLastName( fullAppUserDto.getLastName() );
        appUser.setPhone( fullAppUserDto.getPhone() );
        appUser.setEmail( fullAppUserDto.getEmail() );
        appUser.setGender( fullAppUserDto.getGender() );
        appUser.setActive( fullAppUserDto.isActive() );
        appUser.setUserType( fullAppUserDto.getUserType() );
        appUser.setAppRole( appRoleDtoToAppRole( fullAppUserDto.getAppRole() ) );

        return appUser;
    }

    protected AppUserDto appUserToAppUserDto(AppUser appUser) {
        if ( appUser == null ) {
            return null;
        }

        AppUserDto appUserDto = new AppUserDto();

        appUserDto.setId( appUser.getId() );
        appUserDto.setUsername( appUser.getUsername() );
        appUserDto.setFirstName( appUser.getFirstName() );
        appUserDto.setLastName( appUser.getLastName() );
        appUserDto.setPhone( appUser.getPhone() );
        appUserDto.setEmail( appUser.getEmail() );
        appUserDto.setGender( appUser.getGender() );
        appUserDto.setActive( appUser.isActive() );

        return appUserDto;
    }

    protected AppRoleDto appRoleToAppRoleDto(AppRole appRole) {
        if ( appRole == null ) {
            return null;
        }

        AppRoleDto appRoleDto = new AppRoleDto();

        appRoleDto.setId( appRole.getId() );
        if ( appRole.getName() != null ) {
            appRoleDto.setName( appRole.getName().name() );
        }

        return appRoleDto;
    }

    protected FullAppUserDto appUserToFullAppUserDto(AppUser appUser) {
        if ( appUser == null ) {
            return null;
        }

        FullAppUserDto fullAppUserDto = new FullAppUserDto();

        fullAppUserDto.setId( appUser.getId() );
        fullAppUserDto.setUsername( appUser.getUsername() );
        fullAppUserDto.setPassword( appUser.getPassword() );
        fullAppUserDto.setFirstName( appUser.getFirstName() );
        fullAppUserDto.setLastName( appUser.getLastName() );
        fullAppUserDto.setPhone( appUser.getPhone() );
        fullAppUserDto.setEmail( appUser.getEmail() );
        fullAppUserDto.setGender( appUser.getGender() );
        fullAppUserDto.setActive( appUser.isActive() );
        fullAppUserDto.setUserType( appUser.getUserType() );
        fullAppUserDto.setAppRole( appRoleToAppRoleDto( appUser.getAppRole() ) );

        return fullAppUserDto;
    }
}
