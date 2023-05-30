package com.gahyeonn.ssiach16ex1.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    @PreAuthorize("hasAuthority('write')") //권한 부여 규칙 정의 - spring expression language로 기술
    public String getName() {
        return "Fantastico";
    }
}
