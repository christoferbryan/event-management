package com.events.eventmanagement.point.service;

import com.events.eventmanagement.users.entity.User;

public interface PointService {
    void addPoints(User user, int points);
    int getActiveUserPoints(Long userId);

    int redeemPoints(User user, int totalPrice);

}
