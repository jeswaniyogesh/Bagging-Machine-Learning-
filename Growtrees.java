//package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Growtrees {

Entropy ent;
	
	LinkedList<LinkedList<String>> attr1;
	public Growtrees(Entropy e,LinkedList<String> attr[],int length){
		
		ent=e;
		attr1=new LinkedList<>();
		
		for(int i=0;i<length;i++){
			
			attr1.add( (LinkedList<String>) attr[i].clone());
		}
		
		
	}
	// This Function will Calculate the Gains for all the Attributes and returns Index
	
	
	public int gains(LinkedList<String> attr[],int length,LinkedList<String> flag){
		double max=0;
		int index=0;
		double entropy=ent.entropycalc(attr[length-1]);
		if(flag.contains("1")){
		for(int j=0;j<flag.size();j++){
			
		if(flag.get(j).equals("1")){
		
		double gain= ent.gaincalc(attr[j], attr[flag.size()],entropy);
		//System.out.println(gain);
			if(gain>max){
				max=gain;
				index=j;
				
			}
			
		}
		
		}
		
		return (index);

	}else return -1;
	}
	
     Stack<ArrayList<String>> s=new Stack();
     
     LinkedList<String> zero;
     LinkedList<String> one;

	LinkedList<Integer> a=new LinkedList<Integer>();
	LinkedList<String> name=new LinkedList<String>();
	
	
	/*public void byLevel(Node root){
	     Queue<Node> level  = new LinkedList<>();
	     level.add(root);
	     while(!level.isEmpty()){
	         Node node = level.poll();
	         System.out.print(node.name + " ");
	         if(node.left!= null){
	        	 System.out.println("left");
	         
	         level.add(node.left);
	         }
	         if(node.right!= null){
	        	 
	         System.out.println("right");
	         level.add(node.right);
	         }
	     }
	}
	*/
	// This is one of the base case Function which will check if Result set contains all Zeros
	
	public boolean allZeros(LinkedList<String> attr[],int length){
		
	     Iterator itr=attr[length-1].iterator();
			while(itr.hasNext()){
	
				if(itr.next().equals("1")){
					return false;
				}
			}
			
		
		return true;
	}
	
	// This is One of the base cases in main function to build tree, will check for all ones in Result Set
	
public boolean allOnes(LinkedList<String> attr[],int length){
		
		   Iterator itr=attr[length-1].iterator();
			
			while(itr.hasNext()){
				
				if(itr.next().equals("0")){
					return false;
				}
			}
		
		
		return true;
	}


     Decompose d=new Decompose();
    
    
    /*public boolean Zerodata(ArrayList<String> attr[], int length){
    	
    	for(int i=0;i<length;i++){
    	
    	
    	Iterator itr=attr[i].iterator();
    		
    		while(itr.hasNext()){
    			
    			if(itr.next() != null){
    				return false;
    			}
    			
    		}
    		
    		
    	}
    	
    	return true;
    }*/
    
     // This function will keep a check on Attributes used
     
      public  LinkedList<String> getInitialFlag(LinkedList<String> fileData){
		LinkedList<String> flag=new LinkedList<String>();
		
		Iterator itr= fileData.iterator();
		
		while(itr.hasNext()){
			
			   flag.add("1");
			
			    itr.next();
		}
		return flag;
	}
   
   // This is also one of Base cases and will check for Max no of Attr values in result set
   
   public Node Check(LinkedList<String> attr[], int length){
	   
	   int countzero=0;
	   int countone=0;
	   
	   Iterator itr=attr[length-1].iterator();
	   
	   while(itr.hasNext()){
		   
		   if(itr.next().equals("0")){
			   countzero++;
			  
		   }else countone++;
	   }
	  // System.out.println(countzero+" "+ countone);
	   if(countzero>countone){
		   return new Node("0");
	   }else
		   return new Node("1");
   }
   
   // This function will update the flag on the attribute used to make a decision
   public static LinkedList<String> updateFlag(LinkedList<String> attributeFlag, int index){
		attributeFlag.set(index, "0");
		return attributeFlag;
	}
   
   // This will keep a check on attribute values in case its empty
   public Node Check1(LinkedList<String> attr[],int length){
	   
	   Iterator itr=attr[length-1].iterator();
	   int count=0;
	   int counter=0;
	   while(itr.hasNext()){
		   String value=(String) itr.next();
		   
		   if(value.equals("0")){
			   count++;
		   }else
			   counter++;
	   }
	   
	   if(count>counter){
		   return new Node("0");
	   }else
		   return new Node("1");
   }
   
   // This function will Build a Tree as per the given Dataset
  
	public Node Grow(LinkedList<String> attr[],int length,LinkedList<String> attrnames,LinkedList<String> attributesFlag){
		
       
		if(allZeros(attr,length)){
			//System.out.println("allzeros");
			return new Node("0");
		}else if(allOnes(attr,length)){
			//System.out.println("zero nodes");
			 return new Node("1");
		}	else if(!attributesFlag.contains("1")){
			//System.out.println("attr over");
			return Check(attr,length);
		}
		
		 int index= gains(attr,length,attributesFlag);
			
			if(index==-1){
				System.out.println("-1 indicated");
			}
		
		    LinkedList<String> newFlag1 = new LinkedList<String>();
			LinkedList<String> newFlag2 = new LinkedList<String>();
			newFlag1= (LinkedList<String>) attributesFlag.clone();
			newFlag2= (LinkedList<String>) attributesFlag.clone();
			
			updateFlag(newFlag1, index);
			updateFlag(newFlag2, index);
			
		
		
		//System.out.println(index+"index");
		
		LinkedList<String> arrofZero[]=new LinkedList[length];
		
		for(int i=0;i<length;i++){
			arrofZero[i]=new LinkedList();
		}
		
		arrofZero=d.trunc(attr, length, "0",index);
		
		if(arrofZero[index].isEmpty()){
			//System.out.println("next element");
			return Check1(attr,length);
		}
		
		// index= gains(arrofZero,length,newFlag1);
		
		//System.out.println(index);
		
		LinkedList<String> arrofOne[]=new LinkedList[length];
		
		for(int i=0;i<length;i++){
			arrofOne[i]=new LinkedList();
		}
		
		arrofOne=d.trunc(attr, length, "1", index);
		
		if(arrofOne[index].isEmpty()){
			
		System.out.println("zero elements");
			return Check1(attr,length);
		}
		
		
		return new Node(attrnames.get(index),Grow(arrofZero,length,attrnames,newFlag1),Grow(arrofOne,length,attrnames,newFlag2) );
			
		}
		
	// This will Print the tree
	public static void printTree(Node root, int level)
	{
	    if(root==null)
	         return;
	    
	    printTree(root.right, level+1);
	    
	    if(level!=0){
	        for(int i=0;i<level-1;i++)
	            System.out.print("|\t");
	            System.out.println("|_______"+root.name);
	    }
	    else
	        System.out.println(root.name);
	    printTree(root.left, level+1);
	}  
	
	
	//Get the index for corresponding attribute
	
	public int getIndexofattr(String name,LinkedList<String> attrnames){
		
		
		Iterator itr=attrnames.iterator();
		
		int count=-1;
		
		while(itr.hasNext()){
			
			String val=(String) itr.next();
			count++;
			if(val.equals(name)){
				break;
			}
		}
		
		return count;
		
	}
	
	//Traverse the tree to find the accuracy
	
	public LinkedList<String> traverseTree(Node root, LinkedList<String> attr1[],int length,LinkedList<String> attrnames,LinkedList<String> attrnames1){
		
		LinkedList<String> op=new LinkedList<>();
		int count=0;
		
		
			for(int j=0;j<attr1[0].size();j++){
				
				
				while(root.left!=null&& root.right!=null){
					
					String name=root.left.name;
					
					int index= getIndexofattr(name,attrnames);
					
					if(index!=-1){
						
						String attrval=attr1[index].get(j);
						
						int k=getIndexofattr(attrval,attrnames1);
						
						if(attrval.equals("0"))
							root=root.left;
						else if(attrval.equals("1"))
							root=root.right;
						
					}else if(j==-1){
						
						System.out.println("weird in Decision tree");
						
						
					}
				}
				
				if(root.left==null && root.right==null){
					
					String leaf=root.name;
					
					if(leaf.equals(attr1[length-1].get(j))){
						op.add("1");
					}else
						op.add("0");
				}
			}
			return op;
		}
	
	
	
public int traverseTreeInd(Node root, LinkedList<String> attr1[],int length,LinkedList<String> attrnames,LinkedList<String> attrnames1){
		
        int count=0;
		
		for(int j=0;j<attr1[0].size();j++){
				
				
				while(root.left!=null&& root.right!=null){
					
					String name=root.left.name;
					
					int index= getIndexofattr(name,attrnames);
					
					if(index!=-1){
						
						String attrval=attr1[index].get(j);
						
						int k=getIndexofattr(attrval,attrnames1);
						
						if(attrval.equals("0"))
							root=root.left;
						else if(attrval.equals("1"))
							root=root.right;
						
					}else if(j==-1){
						
						System.out.println("weird in Decision tree");
						}
				}
				
				if(root.left==null && root.right==null){
					
					String leaf=root.name;
					
					if(leaf.equals(attr1[length-1].get(j))){
						count++;
					}
			}
		}
			return count;
		}
	
	// It will give us the accuracy
	public double Findaccuracy(Node root,LinkedList<String> attr1[],int length, LinkedList<String> attrnames, LinkedList<String> attrnames1){
		
		double accuracy=0.0;
		
		int count=0;
		
		int size=attr1[0].size();
		
		count=traverseTreeInd(root,attr1,length,attrnames,attrnames1);
		
		accuracy = ((double)count/(double)size)* 100;
		
		return accuracy;
		
	}
	
	// This will print the tree in given format
	
	public void printTrees(Node root,int flag){
		
		 if (root.left  ==  null|| root.right == null){ 
			 System.out.print(": "+root.name);
			 return;
			 }
			 String a = "";
			 for(int i = 0;i<flag;i++){
			 a = a+"| ";
			 }
			 
			 System.out.println();
			 System.out.print(a+root.name + " = 0");
			 printTree(root.left,++flag);
			 
			 flag--;
			 System.out.println();
			 System.out.print(a+root.name + " = 1");
			 printTree(root.right,++flag);
			}
	
	}
	
	
