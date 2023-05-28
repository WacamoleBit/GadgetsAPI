package com.manu.GadgetAPI.IEndpoints;

import https.apis_uv_mx.gadgets.CreateGadgetRequest;
import https.apis_uv_mx.gadgets.CreateGadgetResponse;
import https.apis_uv_mx.gadgets.DeleteGadgetRequest;
import https.apis_uv_mx.gadgets.DeleteGadgetResponse;
import https.apis_uv_mx.gadgets.FindAllGadgetByDataRequest;
import https.apis_uv_mx.gadgets.FindAllGadgetByDataResponse;
import https.apis_uv_mx.gadgets.FindAllGadgetResponse;
import https.apis_uv_mx.gadgets.FindGadgetByIdRequest;
import https.apis_uv_mx.gadgets.FindGadgetByIdResponse;
import https.apis_uv_mx.gadgets.UpdateGadgetRequest;
import https.apis_uv_mx.gadgets.UpdateGadgetResponse;

public interface IGadgetEndpoint {
    public CreateGadgetResponse createGadget(CreateGadgetRequest request);

    public DeleteGadgetResponse deleteGadget(DeleteGadgetRequest request);

    public UpdateGadgetResponse updateGadget(UpdateGadgetRequest request);

    public FindGadgetByIdResponse findGadgetById(FindGadgetByIdRequest request);

    public FindAllGadgetByDataResponse findGadgetByData(FindAllGadgetByDataRequest request);

    public FindAllGadgetResponse findAllGadget();
}
