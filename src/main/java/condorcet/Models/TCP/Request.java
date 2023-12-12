package condorcet.Models.TCP;

import condorcet.Enums.RequestCRUD;
import condorcet.Enums.RequestType;

public class Request {
    private RequestType requestType;
    private RequestCRUD requestCRUD;
    private String requestMessage;

    public Request(RequestType requestType, String requestMessage){
        this.requestType = requestType;
        this.requestMessage = requestMessage;
    }

    public Request(){

    }

    public RequestCRUD getRequestCRUD() {
        return requestCRUD;
    }

    public void setRequestCRUD(RequestCRUD requestCRUD) {
        this.requestCRUD = requestCRUD;
    }


    public String getRequestMessage() { return requestMessage;}

    public void setRequestMessage(String requestMessage){this.requestMessage = requestMessage;}

    public RequestType getRequestType(){return requestType;}

    public void setRequestType(RequestType requestType){this.requestType = requestType;}


}

