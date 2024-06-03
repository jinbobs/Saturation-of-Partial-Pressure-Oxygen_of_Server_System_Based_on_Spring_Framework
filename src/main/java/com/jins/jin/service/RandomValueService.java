package com.jins.jin.service;

import com.jins.jin.entity.RandomValue;
import com.jins.jin.repository.RandomValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomValueService {

    @Autowired
    private RandomValueRepository randomValueRepository;

    private Random random = new Random();

    public void saveRandomValue(int inputValue) {
        int randomValue = random.nextInt(100); // 0부터 99 사이의 랜덤 값 생성
        RandomValue value = new RandomValue();
        value.setInputValue(inputValue);
        value.setRandomValue(randomValue);
        randomValueRepository.save(value);
    }
}
