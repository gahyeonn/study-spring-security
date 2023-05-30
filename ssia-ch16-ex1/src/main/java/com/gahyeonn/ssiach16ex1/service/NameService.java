package com.gahyeonn.ssiach16ex1.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private Map<String, List<String>> secretNames = Map.of("natalie", List.of("Energico", "Perfecto"),
                                                            "emma", List.of("Fantastico"));

    @PreAuthorize("hasAuthority('write')") //권한 부여 규칙 정의 - spring expression language로 기술
    public String getName() {
        return "Fantastico";
    }

    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretNames(String name) {
        return secretNames.get(name);
    }
}
