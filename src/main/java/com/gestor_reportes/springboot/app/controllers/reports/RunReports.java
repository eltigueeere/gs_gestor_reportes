package com.gestor_reportes.springboot.app.controllers.reports;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RunReports {


    //REP. POR PERIODO

    @PostMapping(value="/form_reportes_bo_periodo")
    public String postPeriodo(@RequestParam Map<String, String> reportes, 
        Model model ) {
        return "redirect:/reportes_bo_periodo";
    }
    

    @Secured({"ROLE_ADMIN", "ROLE_MESA_CONTROL"})
    @GetMapping("/reportes_bo_periodo")
    public String getReportesBoPeroido() {        
        return "vieFromMenu/reportes_bo_periodo";
    }


    //REP. POR Fecha

    @PostMapping(value="/form_reportes_bo_fecha")
    public String postfecha(@RequestParam Map<String, String> reportes, 
        Model model ) {
        return "redirect:/reportes_bo_fecha";
    }
    
    
    @Secured({"ROLE_ADMIN", "ROLE_MESA_CONTROL"})
    @GetMapping("/reportes_bo_fecha")
    public String getReportesBoFecha() {        
        return "vieFromMenu/reportes_bo_fecha";
    }

    //REP. POR CILCO
    @PostMapping(value="/form_reportes_bo_ciclo")
    public String postCiclo(@RequestParam Map<String, String> reportes, 
        Model model ) {
        return "redirect:/reportes_bo_ciclo";
    }
    

    @Secured({"ROLE_ADMIN", "ROLE_MESA_CONTROL"})
    @GetMapping("/reportes_bo_ciclo")
    public String getReportesBoCiclo() {        
        return "vieFromMenu/reportes_bo_ciclo";
    }
}
