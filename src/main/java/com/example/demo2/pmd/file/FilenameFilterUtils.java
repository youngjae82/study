package com.example.demo2.pmd.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class FilenameFilterUtils
{
	private static ArrayList< String > list = new ArrayList< String >( );
    private FilenameFilterUtils( )
    {}
   
   
    /**
     * 해당경로의 해당 확장자를 가지고 있는 파일 리스트를 리턴
     * @param path : 경로
     * @param ex : 확장자명 ex) .txt
     * @return
     * @throws IOException
     */
    public static ArrayList< String > getfilenameFilterList( String path , String ex ) throws Exception
    {
        File base = new File( path );
        if ( base.exists( ) == false )
        {
        	System.out.println( "===========Not exist or not a directory.==========" );
            return list;
        }
        else
        {
            if ( isAvailable( base, ex) )
            {
                FilenameFilterImpl filenameFilter = new FilenameFilterImpl( ex );
                File[] fileList = base.listFiles( filenameFilter );
                if ( fileList != null )
                {
                    for ( int idx = 0 , len = fileList.length ; idx < len ; idx++ )
                    {
                    	if(ex.equals(".java")){
                    		// 파일 확장자 대소문자 구분하지 않고 가지고옴
                    		if(fileList[ idx ].getAbsoluteFile( ).toString( ).contains("App.java") 
                    				|| fileList[ idx ].getAbsoluteFile( ).toString( ).contains("Biz.java")  ){
                    			System.out.println(fileList[ idx ].getAbsoluteFile( ).toString( ).replace("\\", "\\\\"));
                    			list.add( fileList[ idx ].getAbsoluteFile( ).toString( ).replace("\\", "\\\\") );
                    		}
                    	}
                    	else{
                    		System.out.println(fileList[ idx ].getAbsoluteFile( ).toString( ).replace("\\", "\\\\"));
                			list.add( fileList[ idx ].getAbsoluteFile( ).toString( ).replace("\\", "\\\\") );
                    	}
                    }
                }
            }
        	return list;
        }
    }
   
   
    /**
     * Check available directory.
     * @param directory
     * @return boolean result
     */
    private static boolean isAvailable( File directory, String ex) throws Exception
    {
        boolean result = false;
        
        File[] listFiles = directory.listFiles();
        
        for(int i = 0 ; i < listFiles.length ; i++){
        	File file = listFiles[i]; 
        	if(file.isDirectory()){
				if(!file.getName().contains(".dm")){
					try {
						getfilenameFilterList(file.getCanonicalPath().toString(), ex);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
        }

        if ( directory.exists( ) && directory.isDirectory( ) )
        {
            result = true;
        }
        
        return result;
    }
}