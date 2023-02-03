package com.health.healer.service;

import com.health.healer.models.Card;
import com.health.healer.repository.CardRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class CardService {

    @Autowired
    private CardRepositoryImpl cardRepository;
    @Autowired
    private LoginService loginService;

    public void save(Card card, String password, Connection connection){
        cardRepository.save(card, connection);
        loginService.createPgUser(card.getMobile(), password, "user", connection);
    }
}
