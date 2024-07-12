package com.events.eventmanagement.point.service.impl;

import com.events.eventmanagement.point.entity.Point;
import com.events.eventmanagement.point.repository.PointRepository;
import com.events.eventmanagement.point.service.PointService;
import com.events.eventmanagement.users.entity.User;
import com.events.eventmanagement.users.service.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;
    private final UserService userService;

    public PointServiceImpl(PointRepository pointRepository, UserService userService){
        this.pointRepository = pointRepository;
        this.userService = userService;
    }
    @Override
    public void addPoints(User user, int points) {
        Instant now = Instant.now();

        Point point = new Point();
        point.setUser(user);
        point.setPoints(points);
        point.setExpiredAt(now.plus(3, ChronoUnit.MONTHS));

        pointRepository.save(point);
    }

    @Override
    public int getActiveUserPoints(String email) {
        Instant now = Instant.now();
        User userData = userService.getUserByEmail(email);

        return pointRepository.getActiveUserPoints(userData.getId(), now);
    }
}
