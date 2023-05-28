package com.manu.GadgetAPI;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.manu.GadgetAPI.IEndpoints.IGadgetEndpoint;
import com.manu.GadgetAPI.Models.Gadget;
import com.manu.GadgetAPI.Repository.IGadget;

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
import https.apis_uv_mx.gadgets.FindAllGadgetResponse.Data;

@Endpoint
public class GadgetEndpoint implements IGadgetEndpoint {
    private static final String NAMESPACE_URI = "https://apis.uv.mx/gadgets";

    @Autowired
    private IGadget gadgetRepository;

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateGadgetRequest")
    @ResponsePayload
    public CreateGadgetResponse createGadget(@RequestPayload CreateGadgetRequest request) {
        CreateGadgetResponse response = new CreateGadgetResponse();

        Gadget gadget = new Gadget();
        gadget.setName(request.getName());
        gadget.setBrand(request.getBrand());
        gadget.setModel(request.getModel());
        gadget.setPlatform(request.getPlatform());
        gadget.setPrice(request.getPrice());
        gadget.setType(request.getType());

        try {
            gadgetRepository.save(gadget);

            response.setCode(200);
            response.setMessage("OK");

            return response;
        } catch (IllegalArgumentException ex) {
            response.setCode(400);
            response.setMessage("BAD REQUEST");

            System.out.println("Error de API. Gadget entity nulo.");
            ex.printStackTrace();

            return response;
        }
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteGadgetRequest")
    @ResponsePayload
    public DeleteGadgetResponse deleteGadget(@RequestPayload DeleteGadgetRequest request) {
        DeleteGadgetResponse response = new DeleteGadgetResponse();

        try {
            gadgetRepository.deleteById(request.getId());

            response.setCode(200);
            response.setMessage("OK");

            return response;
        } catch (IllegalArgumentException ex) {
            response.setCode(400);
            response.setMessage("BAD REQUEST");

            System.out.println("Error de API. Id nulo");
            ex.printStackTrace();

            return response;
        }

    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateGadgetResponse")
    @ResponsePayload
    public UpdateGadgetResponse updateGadget(@RequestPayload UpdateGadgetRequest request) {
        UpdateGadgetResponse response = new UpdateGadgetResponse();

        try {
            Optional<Gadget> search = gadgetRepository.findById(request.getId());
    
            if (search.isPresent()) {
                Gadget gadget = search.get();
    
                if (!request.getData().getName().isEmpty()) {
                    gadget.setName(request.getData().getName());
                }
    
                if (!request.getData().getBrand().isEmpty()) {
                    gadget.setBrand(request.getData().getBrand());
                }
    
                if (!request.getData().getModel().isEmpty()) {
                    gadget.setModel(request.getData().getModel());
                }
    
                if (!request.getData().getPlatform().isEmpty()) {
                    gadget.setPlatform(request.getData().getPlatform());
                }
    
                if (request.getData().getPrice() != gadget.getPrice()) {
                    gadget.setPrice(request.getData().getPrice());
                }
    
                if (!request.getData().getType().isEmpty()) {
                    gadget.setType(request.getData().getType());
                }

                gadgetRepository.save(gadget);
    
                response.setCode(200);
                response.setMessage("OK");

                return response;
            }
        } catch (IllegalArgumentException ex) {
            response.setCode(400);
            response.setMessage("BAD REQUEST");

            System.out.println("Error de API. Id nulo");
            ex.printStackTrace();
            
            return response;
        }

        response.setCode(200);
        response.setMessage("OK");

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FindGadgetByIdRequest")
    @ResponsePayload
    public FindGadgetByIdResponse findGadgetById(FindGadgetByIdRequest request) {
        FindGadgetByIdResponse response = new FindGadgetByIdResponse();

        try {
            Optional<Gadget> search = gadgetRepository.findById(request.getId());

            if(search.isPresent()){
                Gadget gadget = search.get();
                response.getData().setId(gadget.getId());
                response.getData().setBrand(gadget.getBrand());
                response.getData().setModel(gadget.getModel());
                response.getData().setPlatform(gadget.getPlatform());
                response.getData().setPrice(gadget.getPrice());
                response.getData().setType(gadget.getType());

                response.setCode(200);
                response.setMessage("OK");

                return response;
            }
        } catch (Exception ex) {
            response.setCode(400);
            response.setMessage("BAD REQUEST");

            System.out.println("Error de API. Id nulo");
            ex.printStackTrace();
            
            return response;
        }

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FindAllGadgetByDataRequest")
    @ResponsePayload
    public FindAllGadgetByDataResponse findGadgetByData(FindAllGadgetByDataRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'findGadgetByData'");
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FindGadgetByIdRequest")
    @ResponsePayload
    public FindAllGadgetResponse findAllGadget() {
        FindAllGadgetResponse response = new FindAllGadgetResponse();

        Iterable<Gadget> gadgets = gadgetRepository.findAll();

        for (Gadget gadget : gadgets) {
            Data data = new Data();

            data.setId(gadget.getId());
            data.setName(gadget.getName());
            data.setBrand(gadget.getBrand());
            data.setModel(gadget.getModel());
            data.setPlatform(gadget.getPlatform());
            data.setPrice(gadget.getPrice());
            data.setType(gadget.getType());

            response.getData().add(data);
        }
        
        response.setCode(200);
        response.setMessage("OK");
        
        return response;
    }
}
