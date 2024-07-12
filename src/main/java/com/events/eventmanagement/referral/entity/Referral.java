package com.events.eventmanagement.referral.entity;

import com.events.eventmanagement.users.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Data
@Entity
@Table(name = "referral", schema = "public")
public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "referrer_id", nullable = false)
    private User referrer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "referee_id", nullable = false)
    private User referee;

    @NotNull
    @Column(name = "is_claimed", nullable = false)
    private Boolean isClaimed;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = Instant.now();
    }
}
