package com.mayla.stamps;

import org.apache.commons.math3.linear.RealMatrix;

import com.mayla.core.Component;

public class ResistorStamp extends Stamp {
    
    public ResistorStamp(int n, int m, double h) {
	super(n, m, h);
    }

    @Override
    public void fillStamp(Component comp) {
	double g = 1/comp.getValue();
	
	setValue(stamp_A, comp.getV_pos()-1, comp.getV_pos()-1, g); //(+,+) > G
	setValue(stamp_A, comp.getV_pos()-1, comp.getV_neg()-1, -g); //(+,-) > G
	setValue(stamp_A, comp.getV_neg()-1, comp.getV_pos()-1, -g); //(-,+) > G
	setValue(stamp_A, comp.getV_neg()-1, comp.getV_neg()-1, g); //(-,-) > G
    }
}
