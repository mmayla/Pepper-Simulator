package com.mayla.stamps;

import org.apache.commons.math3.linear.RealMatrix;

import com.mayla.core.Component;

public class CapacitorStamp extends Stamp {

    private double gn;
    
    public CapacitorStamp(int n, int m, double h) {
	super(n, m, h);
    }

    @Override
    public void fillStamp(Component comp) {
	gn = comp.getValue() / this.h;
	double Iegn1 = gn * comp.getInitialVal();

	setValue(stamp_A, comp.getV_pos() - 1, comp.getV_pos() - 1, gn); // (+,+)>gn
	setValue(stamp_A, comp.getV_neg() - 1, comp.getV_pos() - 1, -gn); // (-,+)>-gn
	setValue(stamp_A, comp.getV_pos() - 1, comp.getV_neg() - 1, -gn); // (+,-)>-gn
	setValue(stamp_A, comp.getV_neg() - 1, comp.getV_neg() - 1, gn); // (-,-)>gn

	setValue(stamp_Z, comp.getV_pos() - 1, 0, Iegn1); // (+,1)>Iegn+1
	setValue(stamp_Z, comp.getV_neg() - 1, 0, -Iegn1); // (-,1)>Iegn+1
    }

    @Override
    public void update(Component comp, RealMatrix preX) {
	double vn = getValue(preX, comp.getV_pos() - 1, 0) - getValue(preX, comp.getV_neg() - 1, 0);
	double Iegn1 = gn * vn;

	setValue(stamp_Z, comp.getV_pos() - 1, 0, Iegn1); // (+,1)>Iegn+1
	setValue(stamp_Z, comp.getV_neg() - 1, 0, -Iegn1); // (-,1)>Iegn+1
    }

}
