package com.tmmf.chess.api.service;

import com.tmmf.chess.api.entity.Player;

import java.util.UUID;

public interface SubscriptionService {

    Player subscribe(String name);
    void unsubscription(UUID uuid);

}
