package com.adobe.aem.social.fiwt.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AboutPropertyModel {

    @ValueMapValue
    private String id;

    @ValueMapValue
    private String reverse;

    @Self
    private Resource resource;

    @SlingObject
    private ValueMap leftvalueMap;

    @SlingObject
    private ValueMap rightvalueMap;

    @PostConstruct
    public void init(){
        if(resource.getChild("left") != null) {
            leftvalueMap = resource.getChild("left").getValueMap();
        }else{
            leftvalueMap= null;
        }
        if(resource.getChild("right") != null) {
            rightvalueMap = resource.getChild("right").getValueMap();
        }else{
            rightvalueMap=null;
        }

    }


    public String getId() {
        return id;
    }

    public String getReverse() {
        return reverse;
    }

    public ValueMap getRightvalueMap() {
        return rightvalueMap;
    }

    public ValueMap getLeftvalueMap() {
        return leftvalueMap;
    }
}
