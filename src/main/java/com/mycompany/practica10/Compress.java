package com.mycompany.practica10;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.*;
import java.util.List;
import javax.swing.SwingWorker;
/**
 *
 * @author vivianabenitez
 */
public class Compress {
    
    List<String> files;
    String path;
    
    public Compress(List<String> files, String path) {
        this.files = files;
        this.path = path;
    }
    
  
    public static void compressFiles(List<String> files, String path) throws FileNotFoundException{
            try{
                // Objeto para referenciar a los archivos que queremos comprimir
                BufferedInputStream origin = null;
                // Objeto para referenciar el archivo zip de salida
                FileOutputStream dest = new FileOutputStream(path);
                ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
                // Buffer de transferencia para almacenar datos a comprimir
                byte[] data = new byte[1024];


            Iterator i = files.iterator();
            while(i.hasNext()){
                File filename = (File)i.next();
                FileInputStream fi = new FileInputStream(filename);
                origin = new BufferedInputStream(fi, 1024);
                ZipEntry entry = new ZipEntry( filename.getName() );
                out.putNextEntry( entry );
                // Leemos datos desde el archivo origen y se envían al archivo destino
                int count;
                while((count = origin.read(data, 0, 1024)) != -1){
                    out.write(data, 0, count);
                }
                // Cerramos el archivo origen, ya enviado a comprimir
                origin.close();
            }
            // Cerramos el archivo zip
            out.close();
            } catch (Exception e ){
                e.printStackTrace();
            }
    }

    
 }
