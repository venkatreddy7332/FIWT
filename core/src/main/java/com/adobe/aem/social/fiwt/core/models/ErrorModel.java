package com.adobe.aem.social.fiwt.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ErrorModel {

    @ValueMapValue
    private String errorDescription;

    @ValueMapValue
    private String btnText;

    @ValueMapValue
    private String btnLink;

    @ValueMapValue
    private String id;


    private String error;


    private String erorrTitle;

    @Self
    private SlingHttpServletRequest request;

    @PostConstruct
    public void init(){
        if(request.getParameter("error") != null) {
            error = request.getParameter("error");
        }
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public String getBtnText() {
        return btnText;
    }

    public String getBtnLink() {
        return btnLink;
    }

    public String getId() {
        return id;
    }

    public String getError() {

        return error;
    }

    public String getErorrTitle() {
        return erorrTitle;
    }
}
