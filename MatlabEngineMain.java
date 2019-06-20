/*******************************************************************************
 * Copyright (c) to ...
 *
 * File dedicated to the initiation of the matlab engine from a java file.
 *
 ******************************************************************************/

// package org.jbox2d.testbed.framework.j2d;

import java.awt.BorderLayout;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.*;

import com.mathworks.engine.MatlabEngine;

import static org.jbox2d.testbed.framework.j2d.TestbedSidePanel.setNewImageLabel;


public class MatlabEngineMain {
    // private static final Logger log = LoggerFactory.getLogger(TestbedMain.class);

    // variable for the engine.
    // requires the addition of the .JAR file
    public static MatlabEngine matlab_engine;

    //
    //
    public static String jsonPath;


    private static void InitializeEngine() {
        try{
            System.out.println("Initializing Matlab Engine ...");  //<-- do we need this print ?

            matlab_engine = MatlabEngine.startMatlab(); /*!!!*/

            System.out.println("Initialization Completed");		//<-- do we need this print ?
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void InitializeDetector() {

        // messages are printed from matlab (!?)
        try {
            // Change directory and evaluate function
            String matlabFunDir = "C:\\Users\\Danielle\\Desktop\\ObjectMapper";  //TODO: change this path !!!
            matlab_engine.eval("cd '" + matlabFunDir + "'");
            matlab_engine.feval(0, "intializeDetector");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static String RunMatlab(String imagePath, boolean showDetection, boolean showMapping, double th) {
        
		// messages are printed from matlab
		
        String temp_jsonPath = "";

        try {
            // Change directory and evaluate function
            String matlabFunDir = "C:\\Users\\Danielle\\Desktop\\ObjectMapper";   //TODO: change this path !!!
            matlab_engine.eval("cd '" + matlabFunDir + "'");
			//
            Object result = matlab_engine.feval(1, "Detect_Map", imagePath, th, showDetection, showMapping);
			//
            temp_jsonPath = (String) result;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return temp_jsonPath;
    }
	
	public static void setNewJsonPath(boolean showDetection, boolean showMapping, int threshold){
        //jsonPath = RunMatlab(getImagePath(), showDetection, showMapping, (double)threshold/100); //TODO: IMPORTANT - this is noted only because the function
				//getImagePath()  needs re- implementation
//        jsonPath = "C:\\Users\\Danielle\\Downloads\\jbox2d\\Images\\outputExp.json";
    }

    public static String getImagePath(){
        return chooser.getSelectedFile().getPath();  // TODO: THERE IS  no more choose, this function must be changed !
//        return "C:\\Users\\Danielle\\Downloads\\jbox2d\\Images\\Real_301.jpg";
    }



    public static String getJsonPath(){    // this function does not appear in this file again. might not be needed.
        return jsonPath;
    }

    public static void main(String[] args) {
        
		//
        InitializeEngine();
        InitializeDetector();

        setNewJsonPath(false, false, 98);
//        setNewImageLabel();   // what does this do ?
        jsonPath = "C:\\Users\\Danielle\\Downloads\\jbox2d\\Images\\outputExp.json";   //TODO: change this path !!!

    }


}
