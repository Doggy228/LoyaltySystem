package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;

import edu.doggy228.loyaltyexch.lsemu.Utils;
import edu.doggy228.loyaltyexch.lsemu.api.v1.ApiException;
import edu.doggy228.loyaltyexch.lsemu.modeljson.LoyaltySystem;
import edu.doggy228.loyaltyexch.lsemu.modeljson.LoyaltyUser;
import edu.doggy228.loyaltyexch.lsemu.modeljson.ResponseError;
import edu.doggy228.loyaltyexch.lsemu.modeljson.Trans;
import edu.doggy228.loyaltyexch.lsemu.service.ApiReq;
import edu.doggy228.loyaltyexch.lsemu.service.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Емулятор систем лояльності.", description = "Емуляція основних функцій систем лояльності.")
@RestController
@RequestMapping("/api/v1/lsemu")
public class LoyaltySystemController {
    private AppService appService;

    @Autowired
    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Operation(summary = "Отримання списку систем лояльності.",
            description = "Список сконфігурованих в емуляторі систем лояльності.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успіх. Отримання повного списку систем лояльності.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RspListLoyaltySystem.class))}),
            @ApiResponse(responseCode = "500", description = "Помилка виконання.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))})
    })
    @GetMapping("/ls")
    public ResponseEntity<RspListLoyaltySystem> loyaltySystemGetAll(@RequestHeader HttpHeaders httpHeaders) {
        ApiReq apiReq = new ApiReq(appService, httpHeaders, null);
        try {
            List<edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltySystem> loyaltySystemListDb = appService.getLoyaltySystemRepository().findAll();
            RspListLoyaltySystem rsp = new RspListLoyaltySystem();
            if(loyaltySystemListDb==null || loyaltySystemListDb.isEmpty()){
                rsp.setListLoyaltySystem(new LoyaltySystem[0]);
            } else {
                rsp.setListLoyaltySystem(new LoyaltySystem[loyaltySystemListDb.size()]);
                for(int i=0;i<loyaltySystemListDb.size();i++){
                    rsp.getListLoyaltySystem()[i] = loyaltySystemListDb.get(i).toJson();
                }
            }
            return new ResponseEntity<>(rsp, HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof ApiException) throw e;
            throw new ApiException(apiReq, "Помилка виконання запиту", ""+e, e);
        }
    }

    @Operation(summary = "Отримання інформації про систему лояльності.",
            description = "Список сконфігурованих в емуляторі систем лояльності.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успіх. Інформації системи лояльності.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoyaltySystem.class))}),
            @ApiResponse(responseCode = "500", description = "Помилка виконання.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))})
    })
    @GetMapping("/ls/{id}")
    public ResponseEntity<LoyaltySystem> loyaltySystemGetOne(@Parameter(description = "Ідентифікатор системи лояльності") @PathVariable String id,
                                                                    @RequestHeader HttpHeaders httpHeaders) {
        ApiReq apiReq = new ApiReq(appService, httpHeaders, null);
        try {
            edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltySystem loyaltySystemDb = appService.getLoyaltySystemRepository().findById(id).orElse(null);
            if(loyaltySystemDb==null) throw new ApiException(apiReq, "Система лояльності {"+id+"} не знайдена.", null, null);
            return new ResponseEntity<>(loyaltySystemDb.toJson(), HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof ApiException) throw e;
            throw new ApiException(apiReq, "Помилка виконання запиту", ""+e, e);
        }
    }

    @Operation(summary = "Створення нової системи лояльності.",
            description = "Конфігурація в емуляторі нової системи лояльності.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успіх. Нова система лояльності створена.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoyaltySystem.class))}),
            @ApiResponse(responseCode = "500", description = "Помилка виконання.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))})
    })
    @PostMapping("/ls")
    public ResponseEntity<LoyaltySystem> loyaltySystemCreate(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Форма регістрації системи лояльності.") @RequestBody ReqLoyaltySystemCreate req,
                                                             @RequestHeader HttpHeaders httpHeaders) {
        ApiReq apiReq = new ApiReq(appService, httpHeaders, null);
        try {
            edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltySystem loyaltySystemDb = new edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltySystem();
            loyaltySystemDb.setId(req.getId());
            loyaltySystemDb.setName(req.getName());
            loyaltySystemDb.setVcAlias(req.getVcAlias());
            loyaltySystemDb.setVcName(req.getVcName());
            loyaltySystemDb.setVcRate(req.getVcRate());
            loyaltySystemDb.setVcKoef(req.getVcKoef());
            loyaltySystemDb.setVcScale(req.getVcScale());
            appService.getLoyaltySystemRepository().save(loyaltySystemDb);
            return new ResponseEntity<>(loyaltySystemDb.toJson(), HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof ApiException) throw e;
            throw new ApiException(apiReq, "Помилка виконання запиту", ""+e, e);
        }
    }

    @Operation(summary = "Зміна параметрів системи лояльності.",
            description = "Зміна конфігураційних параметрів системи лояльності.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успіх. Конфігурація системи лояльності змінена.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoyaltySystem.class))}),
            @ApiResponse(responseCode = "500", description = "Помилка виконання.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))})
    })
    @PostMapping("/ls/{id}")
    public ResponseEntity<LoyaltySystem> loyaltySystemUpdate(@Parameter(description = "Ідентифікатор системи лояльності") @PathVariable String id,
                                                             @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Форма оновлення системи лояльності.") @RequestBody ReqLoyaltySystemUpdate req,
                                                             @RequestHeader HttpHeaders httpHeaders) {
        ApiReq apiReq = new ApiReq(appService, httpHeaders, null);
        try {
            edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltySystem loyaltySystemDb = appService.getLoyaltySystemRepository().findById(id).orElse(null);
            if(loyaltySystemDb==null) throw new ApiException(apiReq, "Система лояльності {"+id+"} не знайдена.", null, null);
            if(req.getName()!=null && !req.getName().isEmpty()) loyaltySystemDb.setName(req.getName());
            if(req.getVcAlias()!=null && !req.getVcAlias().isEmpty()) loyaltySystemDb.setVcAlias(req.getVcAlias());
            if(req.getVcName()!=null && !req.getVcName().isEmpty()) loyaltySystemDb.setVcName(req.getVcName());
            if(req.getVcRate()!=null && !req.getVcRate().isEmpty()) loyaltySystemDb.setVcRate(req.getVcRate());
            if(req.getVcKoef()!=null && !req.getVcKoef().isEmpty()) loyaltySystemDb.setVcKoef(req.getVcKoef());
            if(req.getVcScale()>=0) loyaltySystemDb.setVcScale(req.getVcScale());
            appService.getLoyaltySystemRepository().save(loyaltySystemDb);
            return new ResponseEntity<>(loyaltySystemDb.toJson(), HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof ApiException) throw e;
            throw new ApiException(apiReq, "Помилка виконання запиту", ""+e, e);
        }
    }

    @Operation(summary = "Випуск картки лояльності для нового користувача.",
            description = "Картка випускається для вказаної в авторизації системи лояльності для кокретного номера телефону користувача.",
            security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успіх. Нова картка лояльності створена.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoyaltyUser.class))}),
            @ApiResponse(responseCode = "500", description = "Помилка виконання.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))})
    })
    @PostMapping("/lu")
    public ResponseEntity<LoyaltyUser> loyaltyUserCreate(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Форма регістрації користувача.") @RequestBody ReqLoyaltyUserCreate req,
                                                             @RequestHeader HttpHeaders httpHeaders) {
        ApiReq apiReq = new ApiReq(appService, httpHeaders, null);
        try {
            apiReq.checkAuthLoyaltySystem();
            edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltyUser loyaltyUserDb = appService.getCustomRepository().loyaltyUserFindByTelAndSystem(req.getTel(), apiReq.getLoyaltySystem().getId());
            if(loyaltyUserDb==null) {
                loyaltyUserDb = new edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltyUser();
                loyaltyUserDb.setId(UUID.randomUUID().toString());
                loyaltyUserDb.setTel(req.getTel());
                loyaltyUserDb.setLoyaltySystemId(apiReq.getLoyaltySystem().getId());
                loyaltyUserDb.setBalanceAmount(BigDecimal.valueOf(0).setScale(apiReq.getLoyaltySystem().getVcScale(), RoundingMode.HALF_UP).toPlainString());
                appService.getLoyaltyUserRepository().save(loyaltyUserDb);
            }
            return new ResponseEntity<>(loyaltyUserDb.toJson(apiReq.getLoyaltySystem()), HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof ApiException) throw e;
            throw new ApiException(apiReq, "Помилка виконання запиту", ""+e, e);
        }
    }

    @Operation(summary = "Отримання балансу по карті лояльності.",
            description = "Отримання інформації по карті лояльності (в т.ч. балансу). " +
                    "Обов'язково повині бути авторизовані ідентифікатори платіжної системи та користувача.",
            security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успіх. Отримання балансу по карті лояльності.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoyaltyUser.class))}),
            @ApiResponse(responseCode = "500", description = "Помилка виконання.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))})
    })
    @GetMapping("/lu")
    public ResponseEntity<LoyaltyUser> loyaltyUserGet(@RequestHeader HttpHeaders httpHeaders) {
        ApiReq apiReq = new ApiReq(appService, httpHeaders, null);
        try {
            apiReq.checkAuthLoyaltySystem();
            apiReq.checkAuthLoyaltyUser();
            return new ResponseEntity<>(apiReq.getLoyaltyUser().toJson(apiReq.getLoyaltySystem()), HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof ApiException) throw e;
            throw new ApiException(apiReq, "Помилка виконання запиту", ""+e, e);
        }
    }

    @Operation(summary = "Платіжна операція.",
            description = "Виконання платіжної лперації з використанням балів та нарахуванням нових за операцію. " +
                    "Обов'язково повині бути авторизовані ідентифікатори платіжної системи та користувача.",
            security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успіх. Платіж виконано успішно.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Trans.class))}),
            @ApiResponse(responseCode = "500", description = "Помилка виконання.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))})
    })
    @PostMapping("/trans-pay")
    public ResponseEntity<Trans> transPay(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Дані для оплати послуги.") @RequestBody ReqTransPay req,
                                                         @RequestHeader HttpHeaders httpHeaders) {
        ApiReq apiReq = new ApiReq(appService, httpHeaders, null);
        try {
            apiReq.checkAuthLoyaltySystem();
            apiReq.checkAuthLoyaltyUser();
            BigDecimal transAmount = new BigDecimal(req.getTransAmount()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal bonusMax = transAmount.divide(new BigDecimal(apiReq.getLoyaltySystem().getVcRate()), apiReq.getLoyaltySystem().getVcScale(), RoundingMode.HALF_DOWN);
            BigDecimal bonusAmountIn = null;
            switch (req.getBonusType()){
                case FULL:
                    bonusAmountIn = new BigDecimal(apiReq.getLoyaltyUser().getBalanceAmount()).setScale(apiReq.getLoyaltySystem().getVcScale(), RoundingMode.HALF_UP);
                    break;
                case MANUAL:
                    bonusAmountIn = new BigDecimal(req.getBonusAmountIn()).setScale(apiReq.getLoyaltySystem().getVcScale(), RoundingMode.HALF_UP);
                    BigDecimal balanceAmount = new BigDecimal(apiReq.getLoyaltyUser().getBalanceAmount()).setScale(apiReq.getLoyaltySystem().getVcScale(), RoundingMode.HALF_UP);
                    bonusAmountIn = bonusAmountIn.min(balanceAmount);
                    break;
                default:
                    bonusAmountIn = BigDecimal.valueOf(0).setScale(apiReq.getLoyaltySystem().getVcScale(), RoundingMode.HALF_UP);
                    break;
            }
            bonusAmountIn = bonusAmountIn.min(bonusMax);
            BigDecimal payAmount = transAmount.subtract(bonusAmountIn.multiply(new BigDecimal(apiReq.getLoyaltySystem().getVcRate()))).setScale(2, RoundingMode.HALF_DOWN);
            if(payAmount.signum()<0) payAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
            BigDecimal bonusAmountOut = transAmount.multiply(new BigDecimal(apiReq.getLoyaltySystem().getVcKoef())).divide(new BigDecimal(apiReq.getLoyaltySystem().getVcRate())).setScale(apiReq.getLoyaltySystem().getVcScale(), RoundingMode.HALF_UP);
            BigDecimal balanceAmount = new BigDecimal(apiReq.getLoyaltyUser().getBalanceAmount()).subtract(bonusAmountIn).add(bonusAmountOut).setScale(
                    apiReq.getLoyaltySystem().getVcScale(), RoundingMode.HALF_UP);
            edu.doggy228.loyaltyexch.lsemu.modeldb.Trans transDb = new edu.doggy228.loyaltyexch.lsemu.modeldb.Trans();
            transDb.setId(UUID.randomUUID().toString());
            transDb.setLoyaltySystemId(apiReq.getLoyaltySystem().getId());
            transDb.setLoyaltyUserId(apiReq.getLoyaltyUser().getId());
            transDb.setTransDt(Utils.getDateTimeCur());
            transDb.setTransAmount(transAmount.toPlainString());
            transDb.setPayAmount(payAmount.toPlainString());
            transDb.setBonusAmountIn(bonusAmountIn.toPlainString());
            transDb.setBonusAmountOut(bonusAmountOut.toPlainString());
            transDb.setPurpose(req.getPurpose());
            appService.getTransRepository().save(transDb);
            apiReq.getLoyaltyUser().setBalanceAmount(balanceAmount.toPlainString());
            appService.getLoyaltyUserRepository().save(apiReq.getLoyaltyUser());
            return new ResponseEntity<>(transDb.toJson(apiReq.getLoyaltySystem()), HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof ApiException) throw e;
            throw new ApiException(apiReq, "Помилка виконання запиту", ""+e, e);
        }
    }

    @Operation(summary = "Останні 100 операцій по карті лояльності.",
            description = "Отримання останніх операцій по авторизованій карті лояльності. " +
                    "Обов'язково повині бути авторизовані ідентифікатори платіжної системи та користувача.",
            security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успіх. Результат - список останіх операцій.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RspListTrans.class))}),
            @ApiResponse(responseCode = "500", description = "Помилка виконання.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))})
    })
    @GetMapping("/find/trans/byuser")
    public ResponseEntity<RspListTrans> findTransByUser(@RequestHeader HttpHeaders httpHeaders) {
        ApiReq apiReq = new ApiReq(appService, httpHeaders, null);
        try {
            apiReq.checkAuthLoyaltySystem();
            apiReq.checkAuthLoyaltyUser();
            List<edu.doggy228.loyaltyexch.lsemu.modeldb.Trans> transListDb = appService.getCustomRepository().transFindByUserLast100(apiReq.getLoyaltyUser().getId());
            RspListTrans rsp = new RspListTrans();
            if(transListDb==null || transListDb.isEmpty()){
                rsp.setListTrans(new Trans[0]);
            } else {
                rsp.setListTrans(new Trans[transListDb.size()]);
                for(int i=0;i<transListDb.size();i++){
                    rsp.getListTrans()[i] = transListDb.get(i).toJson(apiReq.getLoyaltySystem());
                }
            }
            return new ResponseEntity<>(rsp, HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof ApiException) throw e;
            throw new ApiException(apiReq, "Помилка виконання запиту", ""+e, e);
        }
    }

    @Operation(summary = "Знайти усі картки лояльності за номером телефону.",
            description = "Пошук карток лояльності користувача у всіх системах лояльності.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успіх. Результат - список карток користувача.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RspListLoyaltyUser.class))}),
            @ApiResponse(responseCode = "500", description = "Помилка виконання.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))})
    })
    @GetMapping("/find/user/bytel/{tel}")
    public ResponseEntity<RspListLoyaltyUser> findUserByTel(@Parameter(description = "Номер телефону еористувача") @PathVariable String tel,
                                                      @RequestHeader HttpHeaders httpHeaders) {
        ApiReq apiReq = new ApiReq(appService, httpHeaders, null);
        try {
            List<edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltyUser> loyaltyUserListDb = appService.getCustomRepository().loyaltyUserFindByTel(tel);
            RspListLoyaltyUser rsp = new RspListLoyaltyUser();
            if(loyaltyUserListDb==null || loyaltyUserListDb.isEmpty()){
                rsp.setListLoyaltyUser(new LoyaltyUser[0]);
            } else {
                rsp.setListLoyaltyUser(new LoyaltyUser[loyaltyUserListDb.size()]);
                Map<String, edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltySystem> loyaltySystemMap = new HashMap<>();
                for(int i=0;i<loyaltyUserListDb.size();i++){
                    edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltySystem loyaltySystem = loyaltySystemMap.get(loyaltyUserListDb.get(i).getLoyaltySystemId());
                    if(loyaltySystem==null){
                        loyaltySystem = appService.getLoyaltySystemRepository().findById(loyaltyUserListDb.get(i).getLoyaltySystemId()).orElse(null);
                        if(loyaltySystem==null) continue;
                        loyaltySystemMap.put(loyaltySystem.getId(), loyaltySystem);
                    }
                    rsp.getListLoyaltyUser()[i] = loyaltyUserListDb.get(i).toJson(loyaltySystem);
                }
            }
            return new ResponseEntity<>(rsp, HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof ApiException) throw e;
            throw new ApiException(apiReq, "Помилка виконання запиту", ""+e, e);
        }
    }
}
