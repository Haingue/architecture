package com.imt.pokemon.api.service;

import org.springframework.stereotype.Service;

public interface DuelService {

    Duel createDuel (Pokemon player1, Pokemon player2);

}
