package com.gestor_reportes.springboot.app.common;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class Assets {

    public String readPropertis(String reporte) {
        String ruta = "";
        Properties properties = new Properties();
        java.net.URL url = ClassLoader.getSystemResource("ftp.properties");

        try  {
            properties.load(url.openStream());
        } catch (FileNotFoundException fie) {
            fie.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        /*
        System.out.println(properties.getProperty("hostname"));
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            if (key.equals(reporte )){
                ruta = key;
            }
        }
        */
        ruta = properties.getProperty(reporte);
        return ruta;
    }

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