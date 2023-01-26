package com.gestor_reportes.springboot.app.controllers.reports;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestor_reportes.springboot.app.domain.Conection;


@Controller
public class RunReports {

    @Secured({"ROLE_ADMIN", "ROLE_MESA_CONTROL"})
    @GetMapping("/reportes_bo_periodo")
    public String getReportesBoPeroido() {        
        return "vieFromMenu/reportes_bo_periodo";
    }    
    
    @Secured({"ROLE_ADMIN", "ROLE_MESA_CONTROL"})
    @GetMapping("/reportes_bo_fecha")
    public String getReportesBoFecha() {        
        return "vieFromMenu/reportes_bo_fecha";
    }

    //REP. POR CILCO
    @PostMapping(value="/form_reportes_bo")
    public String postCiclo(@RequestParam Map<String, String> reportes, Model model ) {
        Conection runReports = new Conection("", ""); 
        Object[] __reportes;
        String ruta = "sh /u01/Telcel/CODE/BESRepBoFiles/Shells/";
        String shAllReports = "BESRepBoGenLauncher.sh";
        String shOnereport = "BESRepBoGenerator.sh";
        if( !reportes.get("tipo").isEmpty() ){
            try {
                if( reportes.get("all") != null ) {
                    //EJECUTAR TODOS LOS REPORTES
                    if( reportes.get("tipo").equals("P")) {
                        runReports.runReports(ruta + shAllReports + " " + reportes.get("tipo") + " " + reportes.get("fecha_inicio").replace("-", "/") + " " +reportes.get("fecha_fin").replace("-", "/"));
                        //System.out.println(ruta + shAllReports + " " + reportes.get("tipo") + " " + reportes.get("fecha_inicio").replace("-", "/") + " " +reportes.get("fecha_fin").replace("-", "/"));
                    } else {
                        runReports.runReports(ruta + shAllReports + " " + reportes.get("tipo") + " " + reportes.get("fecha_inicio").replace("-", "/"));
                        //System.out.println(ruta + shAllReports + " " + reportes.get("tipo") + " " + reportes.get("fecha_inicio").replace("-", "/"));
                    }
                } else {
                    //ejecutar individual
                    __reportes = reportes.keySet().toArray();
                    if( reportes.get("tipo").equals("P")) {
                        for( int i=2; i<__reportes.length-2; i++ ) {
                            runReports.runReports(ruta + shOnereport + " " + __reportes[i] + " " + reportes.get("fecha_inicio").replace("-", "/") + " " + reportes.get("fecha_fin").replace("-", "/"));
                            //System.out.println(ruta + shOnereport + " " + __reportes[i] + " " + reportes.get("fecha_inicio").replace("-", "/") + " " + reportes.get("fecha_fin").replace("-", "/") );

                        }
                    } else {
                        for( int i=2; i<__reportes.length-1; i++ ) {
                            runReports.runReports(ruta + shOnereport + " " + __reportes[i] + " " + reportes.get("fecha_inicio").replace("-", "/"));
                            //System.out.println(ruta + shOnereport + " " + __reportes[i] + " " + reportes.get("fecha_inicio").replace("-", "/"));
                        }
                    }
                }
            } catch(Exception ex) {
                System.out.print(ex);
                return "redirect:/";
            }                
        } else {
            System.out.println("No hay hidden");
        }
        return "redirect:/";
    }
    

    @Secured({"ROLE_ADMIN", "ROLE_MESA_CONTROL"})
    @GetMapping("/reportes_bo_ciclo")
    public String getReportesBoCiclo() {        
        return "vieFromMenu/reportes_bo_ciclo";
    }
}
