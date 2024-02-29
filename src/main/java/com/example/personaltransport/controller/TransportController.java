package com.example.personaltransport.controller;

import com.example.personaltransport.model.Route;
import com.example.personaltransport.model.RouteObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personaltransport2")

public class TransportController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{start}/{end}")
    public List<Route> getBusRoutes(@PathVariable String start, @PathVariable String end){
        RouteObj routeList = restTemplate.getForObject(
                "https://transport-routes.azurewebsites.net/api/v1/route/"
                        + start + " bus" + "/" + end + " bus", RouteObj.class);

  /*  @GetMapping("/{transportType}")
    public List<Route> getCarRoutes(@PathVariable String transportType){
        RouteObj routeList = restTemplate.getForObject(
                "https://transport-routes.azurewebsites.net/api/v1/route/"
                        + transportType + "/", RouteObj.class);
*/
        return routeList.getRoutes();
    }

}
