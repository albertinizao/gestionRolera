package com.gestion.rel.domain;

import java.util.Comparator;


public class RelationComparator implements Comparator<Relation>{

	@Override
    public int compare(Relation o1, Relation o2) {
		return o1.getKey().compareTo(o2.getKey());
    }

}
