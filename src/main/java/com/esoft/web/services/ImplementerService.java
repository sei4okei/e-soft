package com.esoft.web.services;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.models.Implementer;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;

import java.util.List;

public interface ImplementerService {
    List<ImplementerDto> findAllImplementers();
    Implementer saveImplementer(Implementer implementer);
    ImplementerDto findImplementerById(long id);
    void updateImplementer(ImplementerDto implementerDto);
    void deleteImplementerById(long implementerId);
}
