//package trees;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Trees_Bag {
	
	// This will give me the Train Data Count
	public static int Traindatacount(String trainset) throws IOException{
		
		BufferedReader in =new BufferedReader(new FileReader( trainset ), 4096 /* buffsize */ );
		
		String firstline=in.readLine();
		
		int count=0;
		
		while(in.readLine()!=null){
			count++;
		}
		
		return count;
	}
	
	
	public static LinkedList<String>[] generateTrainset(int count, String trainset,int col) throws IOException{
		
		LinkedList<String> attr[]=new LinkedList[col];
		
		for(int i=0;i<col;i++){
			attr[i]=new LinkedList();
		}
		//BufferedReader in =new BufferedReader(new FileReader( trainset ), 4096  /*buffsize*/  );
		//System.out.println("before while");
		while(count!=0){
		
		Random r=new Random();
		
		int line=r.nextInt(count);
		
		//System.out.println("line is"+line);
		
        BufferedReader in =new BufferedReader(new FileReader( trainset ), 4096  );
		
		String firstline=in.readLine();
		
		while(line!=0){
			in.readLine();
			line--;
		}
		firstline=in.readLine();
		//System.out.println(firstline);
		
		String [] columns=firstline.split("\\s+");
		
		for(int i=0;i<columns.length;i++){
			
			attr[i].add(columns[i]);
		}
		      count--;
		}
		
		
		return attr;
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		if(args.length != 3)
		{
			System.out.println("You should give exactly 3 argumetns as input which are names of training data and test data files");
			System.exit(1);
			
		}
		System.out.println("Programm in Execution");
		String trainingData = args[0];
		String testData = args[1];
		String iterations=args[2];
		
		
	    BufferedReader in =new BufferedReader(new FileReader( trainingData ), 4096 /* buffsize */ );
	    
	    
	    String aLine=in.readLine() ;
		
		String [] columns=aLine.split("\\s+");
		
		int col=columns.length;
		LinkedList<String> attrnames=new LinkedList<String>();
		for(int j=0;j<columns.length;j=j+2){
			attrnames.add(columns[j]);
		}
		
		BufferedReader in1 =
				new BufferedReader(new FileReader( testData ), 4096 /* buffsize */ );
		
		        
				String aLine1=in1.readLine() ;
				
				String [] columns1=aLine1.split("\\s+");
				
				LinkedList<String> attrnames1=new LinkedList<String>();
				for(int j=0;j<columns1.length;j=j+2){
					attrnames1.add(columns1[j]);
				}
				
		
	    LinkedList<String> attr[]= new LinkedList[columns.length];
		
		LinkedList<String> attr1[]=new LinkedList[columns1.length];
		
		for(int i=0;i<columns.length;i++){
			attr[i]=new LinkedList();
		}
		
		
		for(int i=0;i<columns1.length;i++){
			attr1[i]=new LinkedList();
		}
		
		while ((aLine1=in1.readLine())!=null){      //Read Test Data
			
			
			 columns1=aLine1.split("\\s+");
			
			for(int i=0;i<columns1.length;i++){
				
				attr1[i].add(columns1[i]);
			}
			
	  }
		
		int count=Traindatacount(trainingData);
		
		System.out.println("Examples in Given Training Data is "+count);
		System.out.println();
		
		System.out.println("Below are different Classifiers for given Input of Iterations");
		System.out.println();
		
		int iter=Integer.parseInt(iterations);
		
		LinkedList<String> output[]=new LinkedList[iter];
		
		for(int i=0;i<iter;i++){
			output[i]=new LinkedList();
		}
		
		int c=0;
		do{
			System.out.println(c+1+" classifier with Training Set"+" "+(c+1));
			System.out.println();
			attr=generateTrainset(count,trainingData,col);
			 
			// System.out.println("after func");
		
		int sum=0;
		Iterator itr=attr[4].iterator();
		
		/*while(itr.hasNext()){
			System.out.println("next"+c+" "+itr.next());
			sum++;
		}
		
		System.out.println("First Set"+ sum);*/
		
		Entropy e=new Entropy();
	    Growtrees g=new Growtrees(e,attr,columns1.length);
		LinkedList<String> flag1=new LinkedList<String>();
	    flag1=g.getInitialFlag(attrnames);
	    
	    Node root=g.Grow(attr, columns1.length, attrnames,flag1);
	    
	    
	   output[c]= g.traverseTree(root,attr1,columns1.length,attrnames,attrnames1);
	   
	   Iterator itr1= output[c].iterator();
	   
	   /*while(itr1.hasNext()){
		   System.out.println(c+" "+itr1.next());
	   }*/
	    
	    double acc=g.Findaccuracy(root, attr1, columns1.length, attrnames, attrnames1);
		
	    System.out.println("Accuracy obtained on Test data on "+ (c+1)+" "+"classifier  "+acc+" "+"%");
	     
	  //   System.out.println("The Decision Tree for the Training Data Set");
	
	      g.printTrees(root, 0);
	      
	      System.out.println();
	      System.out.println();
	    
	    for(int i=0;i<columns1.length;i++){
			attr[i].clear();
		}
		
		c++;
		
		}while(c!=iter);
			
		
		double acc= FindBaggingAccuracy(output);
		
		System.out.println("Accuracy obtained After Bagging is "+acc+"%");
		
	}
	
	
	public static double FindBaggingAccuracy(LinkedList<String>[] output){
		int correct=0;
		int wrong=0;
		int count=0;
		
		
		       for(int j=0;j<output[0].size();j++){
		       
	            for(int i=0;i<output.length;i++){
			
				if(output[i].get(j).equals("1")){
					correct++;
					//System.out.println("correct");
				}else{
					wrong++;
					//System.out.println("wrong");
				}
				
				}
	           // System.out.println(correct+"   "+ wrong);
	            if(correct>=wrong){
	            	//System.out.println("in if loop");
	            	count++;
	            }
	            
	            correct=0;
	            wrong=0;
			}
		//System.out.println(count);
		double accuracy=(((double)count)/(double)output[0].size())*100;
		
		return accuracy;
		
	}

}
