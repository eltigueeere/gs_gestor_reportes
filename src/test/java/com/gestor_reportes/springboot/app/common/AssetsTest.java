/*
package com.gestor_reportes.springboot.app.common;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class AssetsTest{

    final Assets assets = new Assets();

    @Test
    public void testReadPropertis(){
        String reporte = "00L";
        String expResult  = "10.119.160.156-svsapcc-MimTlcl17+-/u01/Telcel/test/@10.119.160.156-svsapcc-MimTlcl17+-/u01/Telcel/test/";
        String result = assets.readPropertis(reporte);
        assertEquals(expResult, result);
    }

    @Test
    public void cleanOut() {
        ArrayList<String> files = new ArrayList<String>();
        ArrayList<String> expResult = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();
        files.add("00L");
        files.add("1KQ");
        files.add("");
        expResult.add("00L");
        expResult.add("1KQ");
        result = assets.cleanOut(files);
        assertEquals(expResult, result);

    }

	

}
*/