package models;

import constant.ResponseError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ivald79 on 06/09/2014.
 */
@XmlRootElement(name = "WebResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebResponse {

    private ResponseError responseCode;
    private String responseMessage;


    public ResponseError getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseError responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
