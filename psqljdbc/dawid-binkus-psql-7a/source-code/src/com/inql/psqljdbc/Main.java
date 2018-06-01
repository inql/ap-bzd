package com.inql.psqljdbc;

import com.inql.psqljdbc.ui.UserInterface;

import java.io.File;

public class Main {
    public static void main(String args[]) {
        if(args.length!=1)
            System.out.println("Usage - java -jar psqljdbc.jar \"filename\"");
        else{
            try{
                File file = new File(args[0]);
                String path = file.getAbsolutePath();
                UserInterface userInterface = new UserInterface(path);
                userInterface.run();
            }catch (NullPointerException e){
                e.printStackTrace();
                System.exit(1);
            }

        }


    }
}