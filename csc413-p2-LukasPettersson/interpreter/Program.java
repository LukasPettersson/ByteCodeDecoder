package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.jumpCodes;

import java.util.*;

public class Program {

    private ArrayList<ByteCode> program;

    public Program() {
        program = new ArrayList<>();
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    public void addToList(ByteCode input){ program.add(input); }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently al l labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     */
    public void resolveAddress() {
        /**
         *
         * step back a bit and think what needs to happen here:
         *
         *      for through the program
         *          if the code is an addressable bytecode
         *              run through the program again
         *                  if any of these objects are a label
         *                      if the addressable bytecode's jumpLabel is the same as the the LabelCode
         *                          set the value of the index of the labelcode to the addressable's bytecode label datafield
         */
        for (int i = 0; i < program.size(); i++) { //concerned with finding addressable bytecodes
            if (program.get(i) instanceof jumpCodes) {
                jumpCodes jc = (jumpCodes) program.get(i);

                if (jc.isAddressable()) {
                    for (int j = 0; j < program.size(); j++) { //concerns finding label
                        if (program.get(j) instanceof jumpCodes) {
                            jumpCodes jc1 = (jumpCodes) program.get(j);
                            {
                                if (jc1.isLabel()) {
                                    if (jc.getLabel() != null && jc.getLabel().equals(jc1.getLabel())) {
                                        jc.setLabel(j);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}