package com.health.healer.repository;

import com.health.healer.models.Card;
import org.springframework.stereotype.Component;

@Component
public class CardRepositoryImpl extends JDBCCustomRepositoryImpl<Card, Integer>{

}
