package interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer>     framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    /**
     * Used for dumping the current state of the runTimeStack. 3
     * It will print portions of the stack based on respective 4
     * frame markers.
     * Example [1,2,3] [4,5,6] [7,8]
     * Frame pointers would be 0,3,6
     *
     *
   */
    public void dump () {
        Stack<Integer> FPstack = new Stack<>();
        ArrayList<String> outputList = new ArrayList<>();
        //copy the current stack into the new RTstack
        ArrayList< Integer > RTstack = new ArrayList<>(runTimeStack);
        //copy the framepointer stack into the copy of FPstack
        FPstack.addAll(framePointer);

        while(!RTstack.isEmpty() || FPstack.size() > 1){
            outputList.add(0,"]");

            while((RTstack.size() -1) > FPstack.peek()){ //while the index of the last element is greater than or equal to the value at the top of the FPstack
                outputList.add(0, RTstack.remove(RTstack.size() -1).toString());
                outputList.add(0,",");
            }
            while((RTstack.size() -1) == FPstack.peek()){
                outputList.add(0, RTstack.remove(RTstack.size() -1).toString());
            }
            outputList.add(0,"[");
            FPstack.pop();
        }

        if (FPstack.size() == 1){
            outputList.add(0,"[]");
            FPstack.pop();
        }

        for (String s : outputList) {
            System.out.print(s);
        }
        System.out.print("\n");
    }


    /**
     * returns the top of the runtime stack, but does not remove
     * @return copy of the top of the stack
     */
    public int peek() {
        return this.runTimeStack.get(runTimeStack.size() -1);
    }

    /**
     * push the value i to the top of the stack
     * @param i value to be pushed
     * @return the value popped
     */
    public int push(int i) {
        this.runTimeStack.add(i);
        return i;
    }

    /**
     * removes to the top of the runtime stack.
     * @return the value popped
     *pops the value at the last index of the arraylist
     * SHOULD THIS ALSO REMOVE THE TOP OF THE FRAME POINTER STACK?
     */
    public int pop() {
        if(this.runTimeStack.size() != 0) {
            return this.runTimeStack.remove(this.runTimeStack.size() - 1);
        }
        else return 0;
    }

    /**
     * Takes the top item of the runtime stack and stores
     * it into a offset starting from the current frame
     * @param offset number of slots above current frame marker
     * @return the item just stored
     */
    public int store(int offset) {

        int currentFrame = this.framePointer.peek();
        int rtStackValue = this.pop();

        runTimeStack.set(currentFrame+offset, rtStackValue);

        return rtStackValue;
    }

    /**
     * pop the current frame off the runtime stack.
     * Also removes the frame pointer value from the FramePointer stack.
     */
    public int popFrame() {
        return this.framePointer.pop();
    }
    public int getSize(){
        return this.runTimeStack.size();
    }
    public void addFrame(int frame){
        this.framePointer.add(frame);
    }

    public int peekFramestack(){
        return this.framePointer.peek();
    }
    public int get(int index){
        return this.runTimeStack.get(index);
    }
    public void remove(int index){
        this.runTimeStack.remove(index);
    }

    public int getCurrentFrameSize(){
        return (this.runTimeStack.size() - this.framePointer.peek());
    }
    public String getTopFrame(){
        StringBuilder temp = new StringBuilder();

        for(int i = this.framePointer.peek(); i < this.runTimeStack.size();i++){

            temp.append(this.runTimeStack.get(i).toString());
        }
        return temp.toString();
    }
    public int getFramePointerStackSize(){
        return framePointer.size();
    }
}
