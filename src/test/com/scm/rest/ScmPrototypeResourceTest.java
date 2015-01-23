package com.scm.rest;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Serhii_Bondarenko on 1/23/2015.
 */
public class ScmPrototypeResourceTest {

    ScmPrototypeResource resource = new ScmPrototypeResource();

    @Test
    public void cvsParseTest() throws IOException {
        InputStream stream = new FileInputStream("d:\\slon-data.csv");
        List<List<String>> result=resource.getCSVContent(stream);
       for(List<String> line:result) {
           for(String word :line) {
               System.out.println(word);
           }
       }
    }

}
