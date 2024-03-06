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

    @GetMapping
    public String message(){
        return "your app has deployed";
    }


    @GetMapping("/route/{start}/{end}/{transportType}")
    public List<Route> getBusRoutes(@PathVariable String start, @PathVariable String end, @PathVariable String transportType) {
        String url = "https://routesgithub.azurewebsites.net/route/" + start + "/" + end + "/" + transportType;
        RouteObj routeList = restTemplate.getForObject(url, RouteObj.class);
        return routeList.getRoutes();
    }

    @GetMapping("/route/{transportType}")
    public List<Route> getCarRoutes(@PathVariable String transportType){
        String url = "https://routesgithub.azurewebsites.net/route/" + transportType;
        RouteObj routeList = restTemplate.getForObject(url, RouteObj.class);
        return routeList.getRoutes();
    }

    @GetMapping("/route/end/{end}")
    public List<Route> getBusRoutesFromStation(@PathVariable String end) {
        String url = "https://routesgithub.azurewebsites.net/route/end/" + end;
        RouteObj routeList = restTemplate.getForObject(url, RouteObj.class);
        return routeList.getRoutes();
    }

    @GetMapping("/route/save/{id}")
    public Route saveRoute(@PathVariable Long id) {
        String url = "https://routesgithub.azurewebsites.net/route/save/" + id;
        return restTemplate.getForObject(url, Route.class);
    }
}
