package edu.doggy228.loyaltyexch.lsemu.service;

import edu.doggy228.loyaltyexch.lsemu.api.v1.ApiException;
import edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltySystem;
import edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltyUser;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.springframework.http.HttpHeaders;

import java.util.Locale;
import java.util.StringTokenizer;

@Getter
public class ApiReq {
    private AppService appService;
    private HttpHeaders httpHeaders;
    private LoyaltySystem loyaltySystem;
    private LoyaltyUser loyaltyUser;
    private boolean bearerKeyError;

    public ApiReq(AppService appService, HttpHeaders httpHeaders, String reqParamL) {
        this.appService = appService;
        this.httpHeaders = httpHeaders;
        bearerKeyError = false;
        String authorization = httpHeaders.getFirst("Authorization");
        if (authorization != null && !authorization.isEmpty()) {
            StringTokenizer st = new StringTokenizer(authorization.trim(), " ");
            if (st.countTokens() > 1 && st.nextToken().equals("Bearer")) {
                String bearerKey = st.nextToken();
                try {
                    StringTokenizer st1 = new StringTokenizer(bearerKey, ":");
                    if(st1.countTokens()!=2) throw new Exception("Bad authorization");
                    String loyaltySystemId = st1.nextToken();
                    if(!loyaltySystemId.equals("_")) {
                        loyaltySystem = appService.getLoyaltySystemRepository().findById(loyaltySystemId).orElse(null);
                        if(loyaltySystem==null) throw new Exception("Loyalty system not found.");
                        String loyaltyUserId = st1.nextToken();
                        if(!loyaltyUserId.equals("_")){
                            loyaltyUser = appService.getLoyaltyUserRepository().findById(loyaltyUserId).orElse(null);
                            if(loyaltyUser==null) throw new Exception("Loyalty user not found.");
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    bearerKeyError = true;
                }
            }
        }
    }

    public boolean isLoyaltySystem() {
        if (!bearerKeyError && loyaltySystem != null) return true;
        return false;
    }

    public boolean isLoyaltyUser() {
        if (!bearerKeyError && loyaltyUser != null) return true;
        return false;
    }

    public void checkAuthLoyaltySystem() throws ApiException {
        if (!isLoyaltySystem()) throw new ApiException(this, "Система лояльності не авторизована.", null, null);
    }

    public void checkAuthLoyaltyUser() throws ApiException {
        if (!isLoyaltyUser()) throw new ApiException(this, "Карта лояльності не авторизована.", null, null);
    }
}

