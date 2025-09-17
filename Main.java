import java.io.*;
import java.util.*;
import java.util.stream.*;


public class Main{
    public static void main(String[] blrh) throws IOException{
        Scanner something=new Scanner(System.in);
        System.out.print("Enter a numerical value for k inside of A^k: ");
        int k = something.nextInt();
        

        File dir = new File(".");                       //Im guessing this works similar to how React and HTMl does their pathing for Imports.
                                                                //Since I am not thinking about optimizaion the directiory for the files are just in the src.
                                                                //But as a reference if I were to place these txt files inside of lets say folder "pinecone"
                                                                //the File dir= new File (./pinecone) while the actual path is ./pinecone/textFile.txt
    
    
    
        List<String> files = Arrays.stream(dir.list((d, n) ->                
            n.endsWith(".txt"))).sorted().collect(Collectors.toList());
                //Okay for this section the goal according to the comments left by the professor
                //This List<String> called files will collect all the files in the src, sort them alphabetically, and set them up for the List function Array

            for(String inputFile:files){                        //This section will loop through all avaliable files that were found
                System.out.println("===" + inputFile+"===");    //This will print all the file names. Looking like this "=== Text_File1.txt ==="
                
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));  //This section will open the text files
                                                   //Note that its not reading but instead just opening the book

                // String tempString = null;
                // while((tempString = reader.readLine()) !=null){

                // }
                
                //This is my code
                String lineA = reader.readLine();   //My thought is that since the assignment only denotes A and B 
                String lineB = reader.readLine();   //While our .txt files are only two lines then why not have each line given act as the other side of the equals sign
                                                    //Example lineA, line 1 of .txt is {abc, def} is the same as saying A = {abc, def}. Same goes for B
                

                reader.close();     //Similar to Scanner this calls for the closer of BufferedReader. (Note: Its Buffered! not Buffer)

               
                List<String> A = Arrays.stream(lineA.replace("{", "").replace("}", "").trim().split(",")).map(String::trim).collect(Collectors.toList());
                List<String> B = Arrays.stream(lineB.replace("{", "").replace("}", "").trim().split(",")).map(String::trim).collect(Collectors.toList());
                    //Copying the line that was provided by the professor and the addition of .map(String::trim) & .collection(Collectors.toList())
                    //Will clean up all the text inside of the files. For example textFile.txt has the word "I cant drink milk", this long line of code 
                    //will remove all the whitespace (space between words), splits the whole string via the whitespace (String OneString = "I cant drink milk")
                    //The new stuff is the .map which is better thought of an upgrade attachment to trim. Since now it will further remove ALL whitespace (any whitespace around the String)
                    //This was already used by the proffessor to put it simple it places all the bable into one List element

                //Union
                List<String> onion= new ArrayList<>(A); //So, since this is first come first to be denoted. Our first line WILL be our A
                for(String b: B){                   //Due to this order of line-by-line B will be our second line
                    if(!onion.contains(b)){     //Since we are dealing with Strings rather than int or double our operator will be the String & StringBuilder operators
                        onion.add(b);       //So, .contains(b) (aka if it contains anything) we will attach them to A
                                            //By virtue of Union whatever our A set is our B will simple attach not matter of content. For example A = {abc, def} & B = {null}, A u B = abcdef
                    }
                }
                System.out.println("A u B = "+onion);


                //Concatenation 
                List<String> concate=new ArrayList<>();
                for(String a: A){
                    for(String b: B){
                        concate.add(a+b);
                    }
                }
                System.out.println("A concatinate B ="+concate);


                //A^k 
                List<String> specialK = new ArrayList<>();      //I got lucky enough to see the professor write it down on the board
                specialK.add("");
                for(int innie =1; innie<=k; innie++){       //This is the only difference. I did not do it recursively. I iterated for k amount (value given by user)
                    List<String> answer=new ArrayList<>();
                    for(String part1:specialK){
                        for(String part2:A){
                            answer.add(part1+part2);
                        }
                    }
                    specialK = answer;      //Hopefully in the long run this doesnt cause a cut in the logic since im erasing specialK
                }
                System.out.println("A^"+k+" "+specialK);
            }
            something.close();       
    }
}
