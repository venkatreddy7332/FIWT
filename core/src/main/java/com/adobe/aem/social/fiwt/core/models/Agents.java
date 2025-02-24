package com.adobe.aem.social.fiwt.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Agents {

    @ValueMapValue
    private String agentName;

    @ValueMapValue
    private String agentDesignation;

    @ValueMapValue
    private String instaLink;

    @ValueMapValue
    private String twitLink;

    @ValueMapValue
    private String faceLink;

    @ValueMapValue
    private String agentImgReference;

    @ValueMapValue
    private String alt;


    public String getAgentName() {
        return agentName;
    }

    public String getAgentDesignation() {
        return agentDesignation;
    }

    public String getInstaLink() {
        return instaLink;
    }

    public String getTwitLink() {
        return twitLink;
    }

    public String getFaceLink() {
        return faceLink;
    }

    public String getAgentImgReference() {
        return agentImgReference;
    }

    public String getAlt() {
        return alt;
    }
}
