package com.sample.LLD.Utils;

import com.sample.LLD.dto.response.BaseResponse;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

public abstract class CommonUtils {

  public static String generateUUID() {
    return UUID.randomUUID().toString();
  }

  public static <T> JsonObject getSuccessResponse(T data) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setSuccess(true);
    baseResponse.setData(JsonObject.mapFrom(data));

    return JsonObject.mapFrom(baseResponse);
  }

  public static <T> JsonObject getFailureResponse() {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setSuccess(false);

    return JsonObject.mapFrom(baseResponse);
  }
}
