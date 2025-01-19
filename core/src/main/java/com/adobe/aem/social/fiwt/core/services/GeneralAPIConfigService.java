package com.adobe.aem.social.fiwt.core.services;

import com.adobe.aem.social.fiwt.core.configs.GeneralAPIConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = GeneralAPIConfigService.class,immediate=true)
@Designate(ocd = GeneralAPIConfig.class, factory = true)
public class GeneralAPIConfigService {

    private String api1;

    private String gender;

    @Activate
    public void activate(GeneralAPIConfig config){
        this.api1=config.api1();
        this.gender=config.gender();
    }

    public String getApi1() {
        return api1;
    }

    public String getGender(){
        return gender;
    }
}
