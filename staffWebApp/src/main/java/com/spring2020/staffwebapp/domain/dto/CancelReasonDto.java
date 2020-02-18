package com.spring2020.staffwebapp.domain.dto;

import com.spring2020.staffwebapp.domain.entity.CustomerOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CancelReasonDto
{
    private Long id;
    private LocalDateTime cancelAt;
    private String reason;
    private CustomerOrder order;
}
