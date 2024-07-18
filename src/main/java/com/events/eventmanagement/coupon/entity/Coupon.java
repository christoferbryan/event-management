package com.events.eventmanagement.coupon.entity;

import com.events.eventmanagement.event.entity.Event;
import com.events.eventmanagement.referral.entity.Referral;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "coupon", schema = "public")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_id_gen")
    @SequenceGenerator(name = "coupons_id_gen", sequenceName = "coupons_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "discount", nullable = false)
    private int discount;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @NotNull
    @Column(name = "usage_limit", nullable = false)
    private int usageLimit;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_referral", nullable = false)
    private Boolean isReferral = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "referral_id", referencedColumnName = "id")
    private Referral referral;

    @NotNull
    @Column(name = "expired_at", nullable = false)
    private LocalDate expiredAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
