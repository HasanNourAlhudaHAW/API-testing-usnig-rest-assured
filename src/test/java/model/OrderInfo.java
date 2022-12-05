package model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.transform.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bookId",
        "customerName"
})
@Generated
public class OrderInfo {

    @JsonProperty("bookId")
    private Integer bookId;
    @JsonProperty("customerName")
    private String customerName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bookId")
    public Integer getBookId() {
        return bookId;
    }

    @JsonProperty("bookId")
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @JsonProperty("customerName")
    public String getCustomerName() {
        return customerName;
    }

    @JsonProperty("customerName")
    public String setCustomerName(String customerName) {
        return this.customerName = customerName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String setOrderInfo(String customerName, int bookId) throws JsonProcessingException {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setBookId(bookId);
        orderInfo.setCustomerName(customerName);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(orderInfo);
        return jsonString;
    }

    public String updateOrderCustomerName(String customerName) throws JsonProcessingException {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCustomerName(customerName);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(orderInfo);
        return jsonString;
    }

}