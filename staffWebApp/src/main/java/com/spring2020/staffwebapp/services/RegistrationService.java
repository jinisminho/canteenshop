<<<<<<< HEAD
package com.spring2020.staffwebapp.services;
=======
package com.spring2020.staffwebapp.service;
>>>>>>> hoangpm14

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.entity.AppUser;

public interface RegistrationService
{
    DbResponseDto createUser(AppUser appUser);

}
