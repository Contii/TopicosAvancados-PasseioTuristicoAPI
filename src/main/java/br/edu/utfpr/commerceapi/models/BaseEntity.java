package br.edu.utfpr.commerceapi.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {
    @Id
    private UUID id; 
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseEntity(){
        id = UUID.randomUUID();
        updatedAt = createdAt = LocalDateTime.now();
    }
}