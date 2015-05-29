package com.mayla.stamps;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import com.mayla.core.Component;

public abstract class Stamp {
    protected RealMatrix stamp_A;
    protected RealMatrix stamp_Z;
    private int size;
    private int n,m;
    protected double h;
    
    public Stamp(int n, int m, double h) {
	size = n+m;
	this.n = n;
	this.m = m;
	stamp_A = MatrixUtils.createRealMatrix(size, size);
	stamp_Z = MatrixUtils.createRealMatrix(size, 1);
	this.h = h;
    }
    
    public abstract void fillStamp(Component comp);
    
    protected void setValue(RealMatrix mat, int row, int col, double value) {
	if(row<0 || col<0)
	    return;
	
	mat.setEntry(row, col, value);
    }
    
    protected double getValue(RealMatrix mat, int row, int col) {
	if(row<0 || col<0)
	    return 0;
	
	return mat.getEntry(row, col);
    }
    
    public void update(Component comp, RealMatrix preX){ };
    

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public RealMatrix getStamp_A() {
        return stamp_A;
    }

    public RealMatrix getStamp_Z() {
        return stamp_Z;
    }
}
