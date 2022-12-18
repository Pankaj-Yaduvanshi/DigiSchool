package com.smart.service;

import com.smart.entities.Option;

public interface OptionService {
    Option saveOption(Option option);
    void deleteOption(Integer oId);
    Option getOption(Integer oId);
}
