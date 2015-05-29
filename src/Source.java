import java.io.IOException;

import com.mayla.core.Manager;

public class Source {

    public static void main(String[] args) {
	if(args.length<3) {
	    System.out.println("There must be 3 arguments: <input_path> <time_step> <end_time>");
	    return;
	}
	
	
	//String inpath = "/home/divoo/Desktop/4th year CMP/First Term/Modeling/Assignments/assignment#2/Assignment#2_SPICE_Model/testcases/5.txt";
	String inpath = args[0];
	
	int slashidx = inpath.lastIndexOf("/");
	if (slashidx == -1) {
	    inpath = "./" + inpath;
	    slashidx = 1;
	}
	String outpath = inpath.substring(0, slashidx + 1) + "output_"+ inpath.substring(slashidx + 1);

	System.out.println("Output file path:");
	System.out.println(outpath);
	try {
	    double h = Double.parseDouble(args[1]);
	    double endtime = Double.parseDouble(args[2]);
	    Manager.run(inpath, outpath, endtime, h);
	} catch (IOException e) {
	    System.out.println("ERROR please check your inputs");
	}
    }
}
