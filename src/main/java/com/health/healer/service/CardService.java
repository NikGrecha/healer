package com.health.healer.service;

import com.health.healer.models.Card;
import com.health.healer.models.Visit;
import com.health.healer.repository.CardRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepositoryImpl cardRepository;
    @Autowired
    private LoginService loginService;

    public Card findByMobile(Connection connection, String mobile) {
        return cardRepository.findByMobile(connection, mobile);
    }

    public Card findById(Connection connection, int id) {
        return cardRepository.findById(Card.class, id, connection);
    }

    public void save(Card card, String password, Connection connection){
        cardRepository.save(card, connection);
        loginService.createPgUser(card.getMobile(), password, "pacient", connection);
    }

    public int findIdByLogin(Connection connection, String login) throws SQLException {
        return cardRepository.findIdByLogin(connection, login);
    }
}
