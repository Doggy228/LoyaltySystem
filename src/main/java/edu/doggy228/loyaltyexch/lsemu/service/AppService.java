package edu.doggy228.loyaltyexch.lsemu.service;

import edu.doggy228.loyaltyexch.lsemu.AppConfig;
import edu.doggy228.loyaltyexch.lsemu.repo.CustomRepository;
import edu.doggy228.loyaltyexch.lsemu.repo.LoyaltySystemRepository;
import edu.doggy228.loyaltyexch.lsemu.repo.LoyaltyUserRepository;
import edu.doggy228.loyaltyexch.lsemu.repo.TransRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    private AppConfig appConfig;
    private MessageSource messageSource;
    private LoyaltySystemRepository loyaltySystemRepository;
    private LoyaltyUserRepository loyaltyUserRepository;
    private TransRepository transRepository;
    private CustomRepository customRepository;

    public AppService(AppConfig appConfig, MessageSource messageSource){
        this.appConfig = appConfig;
        this.messageSource = messageSource;
    }

    @Autowired
    public void setLoyaltySystemRepository(LoyaltySystemRepository loyaltySystemRepository){
        this.loyaltySystemRepository = loyaltySystemRepository;
    }

    public LoyaltySystemRepository getLoyaltySystemRepository(){
        return loyaltySystemRepository;
    }

    @Autowired
    public void setLoyaltyUserRepository(LoyaltyUserRepository loyaltyUserRepository){
        this.loyaltyUserRepository = loyaltyUserRepository;
    }

    public LoyaltyUserRepository getLoyaltyUserRepository(){
        return loyaltyUserRepository;
    }

    @Autowired
    public void setTransRepository(TransRepository transRepository){
        this.transRepository = transRepository;
    }

    public TransRepository getTransRepository(){
        return transRepository;
    }

    @Autowired
    public void setCustomRepository(CustomRepository customRepository){
        this.customRepository = customRepository;
    }

    public CustomRepository getCustomRepository(){
        return customRepository;
    }
}
