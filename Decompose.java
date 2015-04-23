//package trees;

import java.util.Iterator;
import java.util.LinkedList;

public class Decompose {

public LinkedList<String>[] trunc(LinkedList<String> attr[], int length, String pos, int index){
		
		LinkedList<String> newdata[]=new LinkedList[length];
		
		for(int i=0;i<length;i++){
			newdata[i]=new LinkedList<String>();
		}
		
		for(int i=0;i<length;i++){
			
			/*if(i==index){
				
			}else{*/
				
			Iterator itr=attr[i].iterator();
			
			Iterator itr1=attr[index].iterator();
			
			while(itr1.hasNext()&& itr.hasNext()){
			
			String next=(String) itr1.next();
			
			if(next.equals(pos)){
				
				newdata[i].add((String) itr.next());
			}else
				itr.next();
			
			}
			
			
		}
		
		
		
		return newdata;
	}

}

