package com.mayla.stamps;

import org.apache.commons.math3.linear.RealMatrix;

import com.mayla.core.Component;

public class CurrentSourceStamp extends Stamp {

    public CurrentSourceStamp(int n, int m, double h) {
	super(n, m, h);
    }

    @Override
    public void fillStamp(Component comp) {
	double ir = comp.getValue();
	setValue(stamp_Z,comp.getV_pos()-1, 0, ir); //(+,1)>Ir
	setValue(stamp_Z,comp.getV_neg()-1, 0, -ir);//(-,1)>Ir
    }
}
