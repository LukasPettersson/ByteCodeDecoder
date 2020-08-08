package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode implements jumpCodes {

    private String codeName;
    private int resolvedLabel;
    private String label;
    @Override
    public void execute(VirtualMachine virtualMachine) {

        int temp = virtualMachine.popRTStack();
        int frameStart = virtualMachine.popFrameStack();

        while(virtualMachine.getRTStackSize() > frameStart){
            virtualMachine.popRTStack();
        }

        virtualMachine.setProgramCounter(virtualMachine.popReturnAddress());
        virtualMachine.pushRTStack(temp);

        if(virtualMachine.isDumb()){

            if(label !=null) {
                String[] tempString = label.split("<");
                System.out.println(codeName + " " + label + " EXIT" + tempString[0] + " : " + temp);
            }
            else
                System.out.println(codeName);
        }
    }

    @Override
    public void init(ArrayList< String > args) {
        codeName = args.get(0);
        if(args.size() > 1){
            label = args.get(1);
        }
    }


    public boolean isLabel(){
        return false;
    }
    public boolean isAddressable() { return true; }
    public String getLabel() {
        return label;
    }
    public void setLabel(int label) {
        this.resolvedLabel = label;
    }
}
