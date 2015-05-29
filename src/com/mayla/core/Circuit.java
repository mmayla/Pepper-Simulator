package com.mayla.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import com.mayla.core.Component.ComponentType;

public class Circuit {
    private int m;
    private int n;
    private List<Component> components;
    private RealMatrix Mat_A;
    private RealMatrix Mat_Z;
    private int vslastseq;
    private List<RealMatrix> results;
    private double endTime;
    private double timeStep;
    public List<String> results_names;

    public Circuit(double endtime, double step) {
	m = 0;
	n = 0;
	components = new ArrayList<Component>();
	vslastseq = 0;
	results = new ArrayList<RealMatrix>();
	this.endTime = endtime;
	this.timeStep = step;
	results_names = new ArrayList<String>();
    }

    public void insertComponent(Component comp) {
	if (comp.type == Component.ComponentType.V || comp.type==ComponentType.L) {
	    m++;
	    comp.seq = ++vslastseq;
	    
	    addCompName(comp);
	}

	int maxnode = comp.v_pos > comp.v_neg ? comp.v_pos : comp.v_neg;

	if (maxnode > n)
	    n = maxnode;

	components.add(comp);
    }

    public void initializeCircuit() {
	Mat_A = MatrixUtils.createRealMatrix(n + m, n + m);
	Mat_Z = MatrixUtils.createRealMatrix(n + m, 1);

	// create all stamps
	for (Component comp : components) {
	    comp.createStamp(n, m, timeStep);
	}
    }

    public void simulateTime() {
	for (double i = timeStep; i <= endTime+0.1; i += timeStep) {
	    if (i == timeStep) {
		calculate();
		results.add(solve());
	    } else {
		updateComponents();
		results.add(solve());
	    }
	}
    }

    public void calculate() {
	for (Component comp : components) {
	    Mat_A = Mat_A.add(comp.componentStamp.getStamp_A());
	    Mat_Z = Mat_Z.add(comp.componentStamp.getStamp_Z());
	}
    }

    public void updateComponents() {
	for (Component comp : components) {
	    // double pr = results.get(results.size()-1).getEntry(1, 0);
	    comp.update(results.get(results.size() - 1));
	}

	Mat_Z = MatrixUtils.createRealMatrix(n + m, 1);
	for (Component comp : components) {
	    Mat_Z = Mat_Z.add(comp.componentStamp.getStamp_Z());
	}
    }

    public RealMatrix solve() {

	DecompositionSolver solver = new LUDecomposition(Mat_A).getSolver();
	RealMatrix Mat_X = solver.solve(Mat_Z);

	return Mat_X;
    }

    int v=0;
    int l=0;
    private void addCompName(Component comp) {
	if(comp.type == ComponentType.V) {
	    results_names.add("Ivsrc"+v);
	    v++;
	}else {
	    results_names.add("I_L"+l);
	    l++;
	}
    }
    
    public RealMatrix getMat_A() {
	return Mat_A;
    }

    public RealMatrix getMat_Z() {
	return Mat_Z;
    }

    public List<RealMatrix> getResults() {
        return results;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

}
