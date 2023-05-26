package com.manu.GadgetAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import com.manu.GadgetAPI.Repository.IGadget;

@Endpoint
public class EndPoint {
    @Autowired
    private IGadget repository;

    
}
