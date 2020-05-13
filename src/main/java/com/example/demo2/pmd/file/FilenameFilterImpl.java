package com.example.demo2.pmd.file;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterImpl  implements FilenameFilter {
    private final String e;
   
   
    /**
     * @param e : 파일 확장자명 ex) .txt
     */
    public FilenameFilterImpl( String e )
    {
        this.e = e;
    }
   
   
    public boolean accept( File dir , String name )
    {
        return name.toLowerCase( ).endsWith( e );
    }
}


