package com.mayla.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.mayla.core.Component.ComponentType;

public class Parser {
    public static Circuit parse(String inPath, double endtime, double timestep)
	    throws FileNotFoundException {
	Scanner sc = new Scanner(new File(inPath));
	Circuit newcircuit = new Circuit(endtime, timestep);

	while (sc.hasNext()) {
	    Component tmpcomp = new Component();
	    tmpcomp.type = parseType(sc.next());
	    tmpcomp.v_pos = parseNode(sc.next());
	    tmpcomp.v_neg = parseNode(sc.next());
	    tmpcomp.value = parseValue(sc.next());
	    tmpcomp.initialVal = parseValue(sc.next());
	    newcircuit.insertComponent(tmpcomp);
	}

	return newcircuit;
    }

    public static void write(String outPath, String content) throws IOException {
	File rfile = new File(outPath);
	rfile.createNewFile();
	FileWriter fw = new FileWriter(rfile);
	
	fw.write(content);
	fw.close();
    }

    private static ComponentType parseType(String type) {
	char type_char = Character.toLowerCase(type.charAt(0));
	if (type_char == 'v')
	    return ComponentType.V;
	else if (type_char == 'r')
	    return ComponentType.R;
	else if (type_char == 'c')
	    return ComponentType.C;
	else {
	    if (type.length() == 1)
		return ComponentType.L;
	    return ComponentType.I;
	}
    }

    private static int parseNode(String node) {
	return Integer.parseInt(Character.toString(node.charAt(1)));
    }

    private static double parseValue(String value) {
	return Double.parseDouble(value);
    }
}
