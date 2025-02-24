package com.adobe.aem.social.fiwt.core.models;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ErrorPageModel {

    @ScriptVariable
    private SlingHttpServletRequest request;

    public String getErrorCode() {
        String errorCode = request.getAttribute("javax.servlet.error.status_code").toString();
        return errorCode != null ? errorCode : "Unknown";
    }

    public String getErrorType() {
        String errorType = request.getAttribute("javax.servlet.error.exception_type").toString();
        return errorType != null ? errorType : "Unknown";
    }

    public String getErrorMessage() {
        String errorMessage = request.getAttribute("javax.servlet.error.message").toString();
        return errorMessage != null ? errorMessage : "No error details available.";
    }
}

