//package trees;

import java.util.Iterator;
import java.util.LinkedList;

public class Entropy {


		
		
		double entro;
		
		//This will calculate Entropy and returns entropy for particular resultset
		
		public double entropycalc(LinkedList<String> result){
			
			if(result.size()==0){
				return 0;
			}
			
			double zero=0;
			double one=0;
			
			Iterator itr=result.iterator();
			
			while(itr.hasNext()){
			
	        if (itr.next().equals("0")){
	        	zero++;
			}
	        else
				one++;
			}
			//System.out.println("zeros"+ zero);
			//System.out.println("ones"+ one);
			double total=zero+one;
			
			if(total==0)
				total=1;
			double zeros=zero/total;
			if(zeros==0){
				zeros=1;
			}
			
			//System.out.println("No of Zeros"+ zeros);
			double ones=one/total;
			
			if(ones==0){
				ones=1;
			}
			
			//System.out.println("No of Ones"+ ones);
			
			double temp=Math.log(zeros)/Math.log(2);
			
			/*if(Double.isNaN(temp)||Double.isInfinite(temp))
				temp=0;
			*/
			double temp1=Math.log(ones)/Math.log(2);
			
			/*if(Double.isNaN(temp1)||Double.isInfinite(temp1))
				
				temp1=0;*/
			 entro= -(zeros)*(temp)  -  (ones)*(temp1);
			 
			// System.out.println(entro+"entropy");
			return entro;
			}
		//This will calculate Inforamtion Gain at every stage to get the next best attribute
		
		public double gaincalc(LinkedList<String> attr, LinkedList<String> result, double entropy){
			
			if(result.size()==0){
				return 0;
			}
			
			double zero=0;
			double one=0;
			
			Iterator itr=attr.iterator();
			
			while(itr.hasNext()){
			
	        if (itr.next().equals("0")){
	        	
	        	zero++;
			}
	        else
				one++;
			}
			//System.out.println("initial zero and one in gain cal"+one+"  "+ zero);
			Iterator itr1=attr.iterator();
			Iterator itr2=result.iterator();
			double negativezero=0;
			double positivezero=0;
			double negativeone=0;
			double positiveone=0;
			while(itr1.hasNext() && itr2.hasNext()){
				
				String attrnext= (String) itr1.next();
				
				String resultnext=(String) itr2.next();
				if(attrnext.equals("0")&& resultnext.equals("0")){
				
					negativezero++;
				}else if( attrnext.equals("0")&& resultnext.equals("1")){
					
					positivezero++;
					
				} else if(attrnext.equals("1")&& resultnext.equals("0")){
					
					negativeone++;
				} else if(attrnext.equals("1")&& resultnext.equals("1")){
					
					positiveone++;
				}
				
			}
			
			//System.out.println("Total no of zeros and positive"+negativezero+" "+ positivezero+" "+negativeone+" "+positiveone);
			double total=0;
			 total=zero+one;
			 if(total==0)
				 total=1;
			if(zero==0){
				zero=1;
			}
			
			if(one==0){
				one=1;
			}
			double totpos=positivezero/zero;
			if(totpos==0.0){
				totpos=1.0;
			}
		//	System.out.println(totpos+ "positive zero");
			double totneg=negativezero/zero;
			if(totneg==0.0){
				totneg=1.0;
			}
		//	System.out.println(totneg+"negative zeros");
			double totposone=positiveone/one;
			if(totposone==0.0){
				totposone=1.0;
			}
			//System.out.println(totposone);
			double totnegone=negativeone/one;
			if(totnegone==0.0){
				totnegone=1.0;
			}
			//System.out.println(totnegone);
			
			double temp2=Math.log(totpos)/Math.log(2);
			
			double temp3=Math.log(totneg)/Math.log(2);
			
			double temp4=Math.log(totposone)/Math.log(2);
			
			double temp5=Math.log(totnegone)/Math.log(2);
			
			/*if(Double.isNaN(temp2)||Double.isInfinite(temp2))
				temp2=0;
			
			if(Double.isNaN(temp3)||Double.isInfinite(temp3))
				temp3=0;
			
			if(Double.isNaN(temp4)||Double.isInfinite(temp4))
				temp4=0;
			
			if(Double.isNaN(temp5)||Double.isInfinite(temp5))
				temp5=0;*/
			
			
			
			double entrozero= -(totpos)*(temp2)  -  (totneg)*(temp3);
			
			if(entrozero==-0)
				entrozero=0;
			
			
			double entroone= -(totposone)*(temp4)  -  (totnegone)*(temp5);
			
			if(entroone==-0)
				entroone=0;
			
		//	System.out.println("entrozer is"+entrozero);
		//	System.out.println("entrozer is"+entroone);
			//System.out.println(zero/total+"answer");
			//System.out.println(one/total);
			double temp=zero/total;
			
			double temp1=one/total;
			//System.out.println(temp);
		//	System.out.println(temp1);
		//	System.out.println( temp1*entroone+ temp*entrozero+ "before entropy");
			double gain= entropy- (temp*entrozero+ temp1*entroone);
		//	System.out.println(gain+"gain");
			
			
			//System.out.println(gain);
			
			return gain;
			
			
		}
		
	}

