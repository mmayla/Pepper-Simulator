package com.mayla.core;

import org.apache.commons.math3.linear.RealMatrix;

import com.mayla.stamps.CapacitorStamp;
import com.mayla.stamps.CurrentSourceStamp;
import com.mayla.stamps.InductorStamp;
import com.mayla.stamps.ResistorStamp;
import com.mayla.stamps.Stamp;
import com.mayla.stamps.VoltageSourceStamp;

public class Component {
    public static enum ComponentType {
	R, I, V, C, L
    }

    ComponentType type;
    int v_pos;
    int v_neg;
    double value;
    double initialVal;
    int seq;
    Stamp componentStamp;

    public Component() {
	seq = 0;
    }

    public Component(ComponentType type, int v_pos, int v_neg, double value,
	    double initialVal) {
	this.type = type;
	this.v_pos = v_pos;
	this.v_neg = v_neg;
	this.value = value;
	this.initialVal = initialVal;
	seq = 0;
    }

    public void createStamp(int n, int m, double h) {

	switch (type) {
	case C:
	    componentStamp = new CapacitorStamp(n, m, h);
	    break;
	case I:
	    componentStamp = new CurrentSourceStamp(n, m, h);
	    break;
	case L:
	    componentStamp = new InductorStamp(n, m, h);
	    break;
	case R:
	    componentStamp = new ResistorStamp(n, m, h);
	    break;
	case V:
	    componentStamp = new VoltageSourceStamp(n, m, h);
	    break;
	default:
	    System.out.println("Error in creating stamp.. type not exist");
	    break;
	}

	componentStamp.fillStamp(this);
    }

    public void update(RealMatrix prevX) {
	componentStamp.update(this, prevX);
    }

    /* getters and setters */
    public ComponentType getType() {
	return type;
    }

    public void setType(ComponentType type) {
	this.type = type;
    }

    public int getV_pos() {
	return v_pos;
    }

    public void setV_pos(int v_pos) {
	this.v_pos = v_pos;
    }

    public int getV_neg() {
	return v_neg;
    }

    public void setV_neg(int v_neg) {
	this.v_neg = v_neg;
    }

    public double getValue() {
	return value;
    }

    public void setValue(double value) {
	this.value = value;
    }

    public double getInitialVal() {
	return initialVal;
    }

    public void setInitialVal(double initialVal) {
	this.initialVal = initialVal;
    }

    public int getSeq() {
	return seq;
    }

    public void setSeq(int seq) {
	this.seq = seq;
    }
}
