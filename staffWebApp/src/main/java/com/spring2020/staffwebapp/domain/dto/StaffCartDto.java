package com.spring2020.staffwebapp.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StaffCartDto
{
    private List<ProductDto> productList;
    @NotNull
    @ApiModelProperty(required = true)
    private String staffUsername;

}
