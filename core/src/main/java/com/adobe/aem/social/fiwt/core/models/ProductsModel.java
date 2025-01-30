package com.adobe.aem.social.fiwt.core.models;

import com.adobe.aem.social.fiwt.core.services.GeneralAPIConfigService;
import com.adobe.aem.social.fiwt.core.services.JsonFromThirdPartyService;
import com.adobe.aem.social.fiwt.core.services.ServiceResourceResolver;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.json.JSONException;
import org.json.JSONObject;
import javax.annotation.PostConstruct;


@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {ComponentExporter.class},
        resourceType = "fiwt/components/custom/products",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class ProductsModel  implements ComponentExporter {

    @ValueMapValue
    private String url;

    private String exportedType="fiwt/components/custom/products";

    private String json;

    @OSGiService
    private JsonFromThirdPartyService jsonFromThirdPartyService;

    private JSONObject jsonObject;

    @OSGiService
    private ServiceResourceResolver serviceResourceResolver;

    @OSGiService
    private GeneralAPIConfigService generalAPIConfigService;

    @JsonInclude
    private String api1;

    @JsonInclude
    private String gender;

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

    @Override
    public String getExportedType() {
        return exportedType;
    }

    public String getApi1() {
        api1=generalAPIConfigService.getApi1();
        return api1;
    }

    public String getGender() {
        gender=generalAPIConfigService.getGender();
        return gender;
    }
}
