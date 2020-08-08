package interpreter;

import interpreter.bytecode.ByteCode;

import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        isDumb;



    protected VirtualMachine(Program program) {
        this.program = program;
    }

    void executeProgram(){
        //sample execute program from the pdf
        programCounter = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<> ();
        isRunning = true;
        isDumb = true; //set this value to change the dumping behavior of the program
        try{
        while(isRunning){
            ByteCode code = program.getCode(programCounter);
            code.execute(this);
            if(isDumb){
                runTimeStack.dump();
            }
            programCounter++;
        }
        }catch(Exception e){
            this.haltProgram();
            this.setDump(false);
            System.out.println(e);
            System.out.println("PROGRAM COUNTER: " + this.programCounter);
        }
    }

    public void setReturnAddress(int returnAddress) {
        this.returnAddress.add(returnAddress);
    }
    public int popReturnAddress(){ return returnAddress.pop();}
    public void setProgramCounter(int programCounter){
        this.programCounter = programCounter;
    }
    public int getProgramCounter(){
        return this.programCounter;
    }
    public void haltProgram(){
        this.isRunning = false;
    }
    public void pushRTStack(int value){
        this.runTimeStack.push(value);
    }
    public int popRTStack(){
        return this.runTimeStack.pop();
    }
    public void store(int offset){ this.runTimeStack.store(offset); }
    public int getTopOfStack(){
        return runTimeStack.peek();
    }
    public int getRTStackSize(){
        return runTimeStack.getSize();
    }
    public void addFrame(int frame){ this.runTimeStack.addFrame(frame); }
    public int peekFrameStack(){
        return this.runTimeStack.peekFramestack();
    }
    public int get(int index){
        return this.runTimeStack.get(index);
    }
    public void setDump(boolean dump){
        this.isDumb = dump;
    }
    //public void removeRTSTack(int index){ this.runTimeStack.remove(index); } //removes an object off the runtimestack at index
    public int popFrameStack(){ return this.runTimeStack.popFrame(); }
    public boolean isDumb(){
        return isDumb;
    }
    public String getTopFrame(){
        return runTimeStack.getTopFrame();
    }
    public int getCurrentFrameSize(){ return this.runTimeStack.getCurrentFrameSize();}

    public int getFramePointerStackSize() {return runTimeStack.getFramePointerStackSize();}
}
