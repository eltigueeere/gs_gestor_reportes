package com.gestor_reportes.springboot.app.controllers.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gestor_reportes.springboot.app.common.Assets;
import com.gestor_reportes.springboot.app.domain.Conection;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

@Controller
public class Send_DistributionController {

    Assets assets = new Assets();

    @Secured({ "ROLE_ADMIN", "ROLE_MESA_CONTROL" })
    @GetMapping("/move_and_backup/{file}")
    public String getMoveFile(@PathVariable(value = "file") String file, Model model) throws Exception {
        Conection conect = new Conection("move_and_backup ".concat(file), "move_and_backup.txt");
        ArrayList<String> mv_file = new ArrayList<String>();
        String reporte="";
        String ftp_parametros="";
        String[] send_parametros;
        conect.exeSsh();
        mv_file = conect.downloadInfo();
        String[] parts = mv_file.get(0).split("/");
        for (int i = 0; i < mv_file.get(0).length(); i++) {
            if(parts[i].equals("done")){
                //Directory
                reporte=parts[i+1];
                break;
            }
          }
          ftp_parametros = assets.readPropertis(reporte);
          try {
            if( !ftp_parametros.isEmpty()) {
              String[] ftp_parametros_multiples = ftp_parametros.split("@");
              for( int i=0; i<=ftp_parametros_multiples.length; i++) {
                if( !ftp_parametros_multiples[i].isEmpty() ){
                  send_parametros = ftp_parametros_multiples[i].split("-");
                  //Inicia FTP
                  System.out.println("ftp_parametros");
                  conect.sendFile("C:\\Users\\jguerrero\\Downloads\\lalo.txt",  send_parametros[3],  send_parametros[1],  send_parametros[0],  send_parametros[2]);
                } // else no esta progrmado para mandar             
              }
            }
          } catch(Exception e ) {
            System.out.println("Error parametros o falta de");
          }
          //Mopver y respladar 
          //conect.exeSshWitchParam("mv_wy " + mv_file.get(0) +" "+ reporte );
        return "redirect:/pre_revision_y_distribucion_de_reportes";
    }

    //This for resourse server
    @RequestMapping(value = "downloadTestFile", method = RequestMethod.GET)
    public void getSteamingFile1(HttpServletResponse response) throws IOException {
        response.setContentType("application/txt");
        response.setHeader("Content-Disposition", "attachment; filename=\"test.txt\"");
        InputStream inputStream = new FileInputStream(new File("C:\\Users\\jguerrero\\Downloads\\lalo.txt"));
        int nRead;
        while ((nRead = inputStream.read()) != -1) {
            response.getWriter().write(nRead);
        }
    }

    //this locate resource on server
    @Secured({ "ROLE_ADMIN", "ROLE_MESA_CONTROL" })
    @GetMapping("/download_file/{file}")
    public String getDownload_file(@PathVariable(value = "file") String file, Model model) throws Exception {
        Conection conect = new Conection("download_files ".concat(file), "download_file.txt");
        ArrayList<String> down_file = new ArrayList<String>();
        conect.exeSsh();
        down_file = conect.downloadInfo();
        conect.downloadFile(down_file.get(0), "C:\\Users\\jguerrero\\Downloads\\".concat(file));
        
        return "redirect:/";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MESA_CONTROL"})
    @GetMapping("/revision_y_distribucion_de_reportes")
    public String showReporFromDir(Model model) throws JSchException, SftpException {
        Conection conect = new Conection("download_folders ", "download_folders.txt");
        ArrayList<String> files = new ArrayList<String>();
        files = assets.cleanOut(conect.downloadInfo());
        model.addAttribute("files", files);
        return "vieFromMenu/revision_y_distribucion_de_reportes.html";
    }
    
    @Secured({"ROLE_ADMIN", "ROLE_MESA_CONTROL"})
    @GetMapping("/revision_y_distribucion_de_reportes/{dir}")
    public String runGetFiles(@PathVariable(value = "dir") String dir) {
        Conection conect = new Conection("download_folders ".concat(dir), "");
        try {
            conect.exeSsh();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "redirect:/revision_y_distribucion_de_reportes";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MESA_CONTROL"})
    @GetMapping("/pre_revision_y_distribucion_de_reportes")
    public String get_revicion_distribucion_rep(Model model) {
        ArrayList<String> files = new ArrayList<String>();
        Conection conect = new Conection("list_folders", "list_folders.txt");
        try {
            conect.exeSsh();
            files = conect.downloadInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("files", files);
        return "vieFromMenu/pre_revision_y_distribucion_de_reportes";
    }
}
