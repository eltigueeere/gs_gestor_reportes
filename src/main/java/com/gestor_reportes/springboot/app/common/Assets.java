package com.gestor_reportes.springboot.app.common;

import java.util.ArrayList;

public class Assets {
    
    public ArrayList<String> cleanOut(ArrayList<String> files) {
        ArrayList<String> return_files = new ArrayList<String>();
        for (String file : files) {
            if( !file.isEmpty()) {
                return_files.add(file);
            }
        }
        return return_files;
    }

}
