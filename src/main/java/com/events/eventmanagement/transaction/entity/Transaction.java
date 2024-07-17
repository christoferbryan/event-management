package com.events.eventmanagement.transaction.entity;

import com.events.eventmanagement.coupon.entity.Coupon;
import com.events.eventmanagement.event.entity.Event;
import com.events.eventmanagement.transactionItem.entity.TransactionItem;
import com.events.eventmanagement.users.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "transaction", schema = "public")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User buyer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @NotNull
    @Column(name = "total_amount", nullable = false)
    private int totalAmount;


    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private Set<TransactionItem> trxItems = new LinkedHashSet<>();

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
