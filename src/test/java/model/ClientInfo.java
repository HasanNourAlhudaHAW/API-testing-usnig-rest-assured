package model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "clientName",
        "clientEmail"
})
@Generated("jsonschema2pojo")
public class ClientInfo {

    @JsonProperty("clientName")
    private String clientName;
    @JsonProperty("clientEmail")
    private String clientEmail;




    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("clientName")
    public String getClientName() {
        return clientName;
    }
    @JsonProperty("clientName")
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @JsonProperty("clientEmail")
    public String getClientEmail() {
        return clientEmail;
    }

    @JsonProperty("clientEmail")
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    public String setClientInfo(String clientName, String clientEmail) throws JsonProcessingException {
        ClientInfo ci= new ClientInfo();
        ci.setClientName(clientName);
        ci.setClientEmail(clientEmail);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(ci);
        return jsonString;
    }


}