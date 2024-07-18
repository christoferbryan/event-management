package com.events.eventmanagement.point.service.impl;

import com.events.eventmanagement.point.entity.Point;
import com.events.eventmanagement.point.repository.PointRepository;
import com.events.eventmanagement.point.service.PointService;
import com.events.eventmanagement.users.entity.User;
import com.events.eventmanagement.users.service.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;

    public PointServiceImpl(PointRepository pointRepository){
        this.pointRepository = pointRepository;
    }
    @Override
    public void addPoints(User user, int points) {
        Instant now = Instant.now();

        Point point = new Point();
        point.setUser(user);
        point.setPoints(points);
        point.setExpiredAt(now.plus(90, ChronoUnit.DAYS));

        pointRepository.save(point);
    }

    @Override
    public int getActiveUserPoints(Long userId) {
        Instant now = Instant.now();

        return pointRepository.getActiveUserPoints(userId, now);
    }

    @Override
    public int redeemUserPoints(User user, int totalPrice) {

        int pointsUsed;
        int totalActivePoints = getActiveUserPoints(user.getId());

        pointsUsed = Math.min(totalPrice, totalActivePoints);

        Point point = new Point();
        point.setUser(user);
        point.setPoints(Math.negateExact(pointsUsed));
        pointRepository.save(point);

        return pointsUsed;
    }

    @Override
    public List<Point> getPointsByUserId(Long userId) {
        return pointRepository.getPointsByUserId(userId);
    }
}
