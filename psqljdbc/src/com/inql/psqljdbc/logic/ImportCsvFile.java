package com.inql.psqljdbc.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImportCsvFile {

    private String csvFile;
    private BufferedReader bufferedReader = null;
    private String line = "";
    private String csvSplitBy = ";";


    public ImportCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    public void readCsvFile(){
        try{

            bufferedReader = new BufferedReader(new FileReader(csvFile));
            while((line=bufferedReader.readLine())!=null){
                //todo impl inserting data to collection (not sure which one :c)
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


}
