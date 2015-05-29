package com.mayla.stamps;

import org.apache.commons.math3.linear.RealMatrix;

import com.mayla.core.Component;

public class InductorStamp extends Stamp {

    double rn;
    public InductorStamp(int n, int m, double h) {
	super(n, m, h);
    }

    @Override
    public void fillStamp(Component comp) {
	rn = comp.getValue()/this.h;
	double vegn1 = -rn * comp.getInitialVal();
	int indx = this.getN() + comp.getSeq()-1;
	
	setValue(stamp_A, comp.getV_pos()-1, indx, 1);
	setValue(stamp_A, comp.getV_neg()-1, indx, -1);
	setValue(stamp_A, indx, comp.getV_pos()-1, 1);
	setValue(stamp_A, indx, comp.getV_neg()-1, -1);
	setValue(stamp_A, indx, indx, -rn);
	
	setValue(stamp_Z, indx, 0, vegn1);
    }
    
    @Override
    public void update(Component comp, RealMatrix preX) {
	int indx = this.getN() + comp.getSeq()-1;
	double ln = preX.getEntry(indx, 0);
	double vegn1 = -rn * ln;
	
	setValue(stamp_Z, indx, 0, vegn1);
    }

}
