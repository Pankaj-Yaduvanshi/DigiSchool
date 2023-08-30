package com.smart.serviceImp;

import com.smart.entities.Option;
import com.smart.repository.OptionRepository;
import com.smart.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OptionServiceImp implements OptionService {
    @Autowired
    private OptionRepository optionRepository;
    @Override
    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public void deleteOption(Integer oId) {
        optionRepository.deleteById(oId);
    }
    @Override
    public Option getOption(Integer oId) {
        return optionRepository.findById(oId).orElseThrow();
    }
}
