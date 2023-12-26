package com.esoft.web.services;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.models.Implementer;

import java.util.List;

public interface ImplementerService {
    List<ImplementerDto> findAllImplementer();
}
