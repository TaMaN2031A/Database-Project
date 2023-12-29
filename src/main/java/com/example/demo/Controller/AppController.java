package com.example.demo.Controller;

import com.example.demo.DTO.AppGetDTO;
import com.example.demo.Model.App;
import com.example.demo.Service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/app")
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addApp(@RequestBody App app) {
        return appService.addApp(app);
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateApp(@RequestBody App app) {
        return appService.updateApp(app);
    }

    @GetMapping("/getApps/{name}")
    public List<AppGetDTO> getApps(@PathVariable String name) {
        return appService.getApplications(name);
    }

    @PostMapping("/replyToApp/{reply}/{idOfApp}")
    public ResponseEntity<Map<String, String>> replyToApplication(@PathVariable String reply, @PathVariable String idOfApp) {
        return appService.replyToApplication(reply, idOfApp);
    }
}
