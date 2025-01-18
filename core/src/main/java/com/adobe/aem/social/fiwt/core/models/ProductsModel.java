package com.adobe.aem.social.fiwt.core.models;

import com.adobe.aem.social.fiwt.core.services.JsonFromThirdPartyService;
import com.adobe.aem.social.fiwt.core.services.ServiceResourceResolver;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.json.JSONException;
import org.json.JSONObject;
import javax.annotation.PostConstruct;


@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductsModel {

    @ValueMapValue
    private String url;

    private String json;

    @OSGiService
    private JsonFromThirdPartyService jsonFromThirdPartyService;

    private JSONObject jsonObject;

    @OSGiService
    private ServiceResourceResolver serviceResourceResolver;

    @PostConstruct
    protected void init(){
        if (url != null && jsonFromThirdPartyService != null){
          json=  jsonFromThirdPartyService.getJsonString(url);
            try {
                jsonObject=new JSONObject(json);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
        serviceResourceResolver.getResourceResolver().getResource("/content/fiwt");

    }

    public String getUrl() {
        return url;
    }

    public String getJson() {
        return json;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
