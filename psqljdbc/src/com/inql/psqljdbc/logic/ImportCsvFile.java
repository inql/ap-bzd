package com.inql.psqljdbc.logic;

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

    public ArrayList<List<String>> getDataFromCsvFile(){
        ArrayList<List<String>> dataSet = new ArrayList<>();
        try{
            bufferedReader = new BufferedReader(new FileReader(csvFile));

            //first line - column names
            line = bufferedReader.readLine();
            dataSet.add(Arrays.asList(line.split(csvSplitBy)));
            //data
            while((line=bufferedReader.readLine())!=null){
                dataSet.add(Arrays.asList(line.split(csvSplitBy)));
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
