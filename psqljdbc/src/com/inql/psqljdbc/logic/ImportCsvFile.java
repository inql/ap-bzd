package com.inql.psqljdbc.logic;

import com.inql.psqljdbc.model.Data;

import java.io.*;
import java.util.*;

public class ImportCsvFile {

    private String csvFile;
    private BufferedReader bufferedReader = null;
    private String line = "";
    private String csvSplitBy = ";";


    public ImportCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    public Data getDataFromCsvFile(){
        Data data = new Data();
        try{
            bufferedReader = new BufferedReader(new FileReader(csvFile));

            //first line - column names
            line = bufferedReader.readLine();
            data.setColumnNames(Arrays.asList(line.split(csvSplitBy)));
            //data
            data.setValues(new ArrayList<>());
            while((line=bufferedReader.readLine())!=null){
                data.getValues().add(Arrays.asList(line.split(csvSplitBy)));
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
        return data;
    }


}
