package edu.doggy228.loyaltyexch.lsemu.api.v1;

import edu.doggy228.loyaltyexch.lsemu.modeljson.ResponseError;
import edu.doggy228.loyaltyexch.lsemu.service.ApiReq;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private ApiReq apiReq;
    protected String msg;

    public ApiException(ApiReq apiReq, String msg, String message, Throwable cause){
        super(message, cause);
        this.msg = msg;
    }

    public ResponseError createResponseError(){
        return new ResponseError(msg, toString());
    }

    @Override
    public String toString() {
        String res = getMessage();
        if(res==null || res.isEmpty()) return "Api exception";
        return res;
    }
}
