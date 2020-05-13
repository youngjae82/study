package com.example.demo2.pmd;


public enum ResultColumn {
	Problem(0),
	Package(1),
	File(2),
	Priority(3),
	Line(4),
	Description(5),
	Rule_Set(6),
	Rule(7);
	
	int index;
	ResultColumn(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
}