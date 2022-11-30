package com.gestor_reportes.springboot.app.controllers.reports;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Send_DistributionController {
    
    @Secured({"ROLE_ADMIN", "ROLE_MESA_CONTROL"})
    @GetMapping("/revision_y_distribucion_de_reportes")
    public String get_revicion_distribucion_rep(Model model) {
        return "vieFromMenu/revision_y_distribucion_de_reportes";
    }
}
