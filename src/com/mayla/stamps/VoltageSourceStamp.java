package com.mayla.stamps;

import com.mayla.core.Component;

public class VoltageSourceStamp extends Stamp {
    public VoltageSourceStamp(int n, int m, double h) {
	super(n, m, h);
    }

    @Override
    public void fillStamp(Component comp) {
	double vr = comp.getValue();
	int indx = this.getN() + comp.getSeq()-1;
	
	setValue(stamp_A, comp.getV_pos()-1, indx, 1); //(+,r)>1
	setValue(stamp_A, comp.getV_neg()-1, indx, -1);//(-,r)>-1
	setValue(stamp_A, indx, comp.getV_pos()-1, 1);//(r,+)>1
	setValue(stamp_A, indx, comp.getV_neg()-1, -1);//(r,-)>-1
	
	setValue(stamp_Z, indx, 0, vr); //(r,1)>Vr
    }

}
