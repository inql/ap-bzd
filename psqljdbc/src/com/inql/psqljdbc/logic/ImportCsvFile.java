package com.inql.psqljdbc.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ImportCsvFile {

    private String csvFile;
    private BufferedReader bufferedReader = null;
    private String line = "";
    private String csvSplitBy = ";";


    public ImportCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    public Set<List<String>> getDataFromCsvFile(){
        Set<List<String>> dataSet = new HashSet<>();
        try{
            bufferedReader = new BufferedReader(new FileReader(csvFile));

            //first line - column names
            line = bufferedReader.readLine();
            Collections.addAll(Arrays.asList(line.split(csvSplitBy)));
            //data
            while((line=bufferedReader.readLine())!=null){
                Collections.addAll(Arrays.asList(line.split(csvSplitBy)));
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
        return dataSet;
    }


}
