package com.example.cameltestb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @UuidGenerator
    @Column(name = "USER_ID", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "CREATE_ON", nullable = false)
    private LocalDateTime createOn;

    @Column(name = "UPDATE_ON")
    private LocalDateTime updateOn;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @PrePersist
    private void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createOn = now;
        updateOn = now;
    }

    @PreUpdate
    private void onUpdate() {
        updateOn = LocalDateTime.now();
    }
}
