package com.events.eventmanagement.point.repository;

import com.events.eventmanagement.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("SELECT SUM(p.points) AS points FROM Point p WHERE p.user.id = :userId AND p.expiredAt > :now")
    Integer getActiveUserPoints(@Param("userId") Long userId, @Param("now") Instant now);
}
