
package interpreter;

import interpreter.bytecode.ByteCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    
    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts. Can also use the split function in the String class.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
    //reads in a byteCode one line at a time. and catches exceptions if one is thrown
         String line = null;
         String parsedLine[];
         ArrayList< String > temp = new ArrayList<>();
         Program program = new Program();
        //this is jank but works for now
        try{
            while ((line = this.byteSource.readLine()) != null) {
                //tokenize line

                parsedLine = line.split(" ");
                temp.clear();
                /** the line below can be replaced with collections.addAll, not sure if this is correct usage
                 * for(int i = 0; i < parsedLine.length; i++){
                 *                     temp.add(parsedLine[i]);
                 *                 }
                 */
                Collections.addAll(temp, parsedLine);

                //create instance of the bytecode class
                String className = CodeTable.getClassName(parsedLine[0]);
                Class classBlueprint = Class.forName("interpreter.bytecode." + className); //Interpreter.bytecode.className

                //Each bytecode doesnt have a contructor.
                Constructor codeConstructor = classBlueprint.getDeclaredConstructor();

                ByteCode bytecode = (ByteCode) codeConstructor.newInstance();

                //store these bytecodes in an ArrayList<>;
                bytecode.init(temp);
                program.addToList(bytecode);

            }
        }
        catch (Exception e) {
            System.out.println("threw exception in loadCodes: "+ e);
            System.exit(-1);
        }

        //need a call to programs resolveAddress function...
        //this is the next task
        program.resolveAddress();

        return program;
    }
}
