Readme File:

Source Code consists of Following Files:

1) Trees_Bag.java (Main Executable)
2) Node.java
3) Entropy.java
4) Decompose.java
5) Growtrees.java

Bagging Description:

I have used Decision Trees Classifier from Project 1. The Input will be:

1) Training Data
2) Test Data
3) Number of Iterations ( This classifies Number of Decision Trees User wants to create for classification)

Some Improvisations in Base Decision Tree Classifier code. I have made 3 more functions to the existing classifier which will Generate Different Training Sets,
Provide us the Training Examples Count and will find the Accuracy over all Classifiers.

Here, As the Input data is given and the program starts executing, The program will Internally generate different training sets depending on the given Iterations
value. For example, if Iteration value is 2, Program will generate 2 Training sets with replacement algorithm. Once generated, We can see a Different classifiers 
depending on different Training Sets with their Accuracy. At the last, We can see a Combined accuracy on all the classifiers.

Experiment: After getting the Input Desired, I Created Different training Sets internally with Replacement Algorithm. Then, I classified Decision Trees with
            the Given Training Sets Individually and Calculated the accuracy on classifiers. After Generating all Classifiers, I calculated Accuracy on all the 
            classifiers and found that the Accuracy is Increased as compared to Individual Classifiers. Since Training Data Selection is Random, Sometimes the
            result is undesired.(But chances are Rare).
           
            Here, I have used Analogy that if the Classifier is correctly classifying the Test input data, then I am storing that as "1" in List.
            If Classifier is wrongly classifying, then I am Storing it as a "0" in my list.
            I am having an Array of Linked List for this task which will store all the output predictions done by different classifiers in the form of "0" or "1".
            After doing so, I am checking the output given by different classifiers and if more classifiers has predicted correctly i.e "1" then the counter is 
            incremented, means it is correctly classified. If more classifiers are predicting it wrongly, then counter is not incremented. After having the count,
            I found the Accuracy on overall Test Input.

NOTE: The Program can have Binary Features and Binary class labels. i.e The program will work if Features are Binary (0 or 1) and Class labels are Binary (0 or 1).

The Program was Compiled using Eclipse IDE and on Command Prompt.

 To compile File:

 1) javac Trees_Bag.java

 2) To Run File:    java Trees_Bag  {Training Data}  {TestData} {No_of_Iterations}example(2)

