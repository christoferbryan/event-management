package com.events.eventmanagement.point.service;

import com.events.eventmanagement.point.entity.Point;
import com.events.eventmanagement.users.entity.User;

import java.util.List;

public interface PointService {
    void addPoints(User user, int points);
    int getActiveUserPoints(Long userId);
    int redeemUserPoints(User user, int totalPrice);

    List<Point> getPointsByUserId(Long userId);
}
