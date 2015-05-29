package com.mayla.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.math3.linear.RealMatrix;

public class Manager {

    public static void run(String inpath, String outpath, double endtime,
	    double timestep) throws IOException {

	Circuit circuit = Parser.parse(inpath, endtime, timestep);
	circuit.initializeCircuit();
	circuit.simulateTime();
	String output = Manager.resultsToString(circuit.getResults(),
		circuit.results_names, circuit.getN(), circuit.getM(),
		timestep, endtime);
	Parser.write(outpath, output);
    }

    private static String resultsToString(List<RealMatrix> results,
	    List<String> names, int n, int m, double h, double endtime) {
	String output = "";

	// voltage results
	double ctime;
	for (int i = 0; i < n; i++) {
	    ctime = h;
	    output += "V" + (i + 1) + "\n";
	    for (int k = 0; k < results.size(); k++) {
		output += (ctime) + " " + results.get(k).getEntry(i, 0) + "\n";
		ctime = ctime + h;
	    }
	    output += "\n";
	}

	// others
	int nmidx = 0;
	for (int i = n; i < m + n; i++) {
	    ctime = h;
	    output += names.get(nmidx++) + "\n";
	    for (int k = 0; k < results.size(); k++) {
		output += (ctime) + " " + results.get(k).getEntry(i, 0) + "\n";
		ctime = ctime + h;
	    }
	}

	return output;
    }

    public static void printContent(RealMatrix mat) {
	int rows = mat.getRowDimension();
	int cols = mat.getColumnDimension();

	for (int r = 0; r < rows; r++) {
	    for (int c = 0; c < cols; c++) {
		System.out.print(mat.getEntry(r, c) + " ");
	    }
	    System.out.println();
	}
    }
}
