package com.gestor_reportes.springboot.app.common;

import java.util.ArrayList;

public class Assets {

    ArrayList<String> activaciones = new ArrayList<String>();
    ArrayList<String> cobranza = new ArrayList<String>();
    ArrayList<String> errores = new ArrayList<String>();
    ArrayList<String> facturación = new ArrayList<String>();
    ArrayList<String> tasacion = new ArrayList<String>();

    public ArrayList<String> cleanOut(ArrayList<String> files) {
        ArrayList<String> return_files = new ArrayList<String>();
        for (String file : files) {
            if( !file.isEmpty()) {
                return_files.add(file);
            }
        }
        return return_files;
    }

    public ArrayList<String> getActivaciones() {
        return activaciones;
    }

    public ArrayList<String> getCobranza() {
        return cobranza;
    }

    public ArrayList<String> getErrores() {
        return errores;
    }

    public ArrayList<String> getFacturación() {
        facturación.add("METRICAS_DIARIO");
        return facturación;
    }

    public ArrayList<String> getTasacion() {
        tasacion.add("00L");
        return tasacion;
    }

    
}
