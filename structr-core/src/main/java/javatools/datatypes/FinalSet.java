/**
 * Copyright (C) 2010-2015 Morgner UG (haftungsbeschränkt)
 *
 * This file is part of Structr <http://structr.org>.
 *
 * Structr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Structr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Structr.  If not, see <http://www.gnu.org/licenses/>.
 */
package javatools.datatypes;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Set;

import javatools.administrative.D;
/** 
This class is part of the Java Tools (see http://mpii.de/yago-naga/javatools).
It is licensed under the Creative Commons Attribution License 
(see http://creativecommons.org/licenses/by/3.0) by 
the YAGO-NAGA team (see http://mpii.de/yago-naga).
  

  
 

This class provides a very simple container implementation with zero overhead.
A FinalSet bases on a sorted, unmodifiable array. The constructor
can either be called with a sorted unmodifiable array (default constructor)
or with an array that can be cloned and sorted beforehand if desired. 
Example:
<PRE>
   FinalSet&lt;String&gt; f=new FinalSet("a","b","c");
   // equivalently: 
   //   FinalSet&lt;String&gt; f=new FinalSet(new String[]{"a","b","c"});
   //   FinalSet&lt;String&gt; f=new FinalSet(SHALLNOTBECLONED,ISSORTED,"a","b","c");
   System.out.println(f.get(1));
   --&gt; b
</PRE>
*/
public class FinalSet<T extends Comparable<?>> extends AbstractList<T> implements Set<T>{
  /** Holds the data, must be sorted */
  public T[] data;  
  /** Constructs a FinalSet from an array, clones and sorts the array if indicated.
   * @param clone whether to clone the supplied data or not
   * @param a the data for the Set
   */
  @SuppressWarnings("unchecked")
  public FinalSet(boolean clone,T... a) {
    if(clone) {
      Comparable<?>[] b=new Comparable[a.length];
      System.arraycopy(a,0,b,0,a.length);
      a=(T[])b;
    }
    Arrays.sort(a);
    data=a;
  }
  /** Constructs a FinalSet from an array that does not need to be cloned
   * @param a the objects
   */
  public FinalSet(T... a) {
    this(false,a);
  }
  /** Tells whether x is in the container
   * @param x the object to search
   * @return true if x is in the container, false if not
   */
  public boolean contains(T x) {
    return(Arrays.binarySearch(data,x)>=0);
  }
  /** Returns the position in the array or -1
   * @param x the object to search
   * @return the index
   */
  public int indexOf(T x) {
    int r=Arrays.binarySearch(data,x);
    return(r>=0?r:-1);
  }
  /** Returns the element at position i
   * @param i the index
   * @return the element at the index
   */
  public T get(int i) {
    return(data[i]);
  }
  
  /** Returns the number of elements in this FinalSet
   * @return the size of the container
   */
  public int size() {
    return(data.length);
  }
  
  /** Test routine
   * @param args the arguments
   */
  public static void main(String[] args) {
    FinalSet<String> f=new FinalSet<String>("b","a","c");
    D.p(f.get(1));
  }
}
