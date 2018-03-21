package com.inql.psqljdbc;

import com.inql.psqljdbc.logic.ImportCsvFile;
import com.inql.psqljdbc.ui.DatabaseLogging;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String args[]) {
//        DatabaseLogging databaseLogging = new DatabaseLogging();
//        databaseLogging.connectToDatabase();
        String csvPath = args[0];
        ImportCsvFile importCsvFile = new ImportCsvFile(csvPath);
        Set<List<String>> result = importCsvFile.getDataFromCsvFile();
        Iterator itr = result.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next().toString());
        }
    }
}