import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void TestStudentArray(){
        String msg = "";
        StudentArrayDeque<Integer> toTest = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> theGod = new ArrayDequeSolution<>();
        while(true){//continue calling the methods of array until the bug is found
            if(theGod.size()==0 || toTest.size() == 0) {//make it out in case of NullException
                Integer rand = StdRandom.uniform(2);
                Integer toAdd = StdRandom.uniform(10);//add a random item between 0 to 10
                if(rand == 1){//rand == 1
                    toTest.addFirst(toAdd);
                    theGod.addFirst(toAdd);
                    msg = msg.concat("addFirst("+toAdd+")\n") ;
                }
                else{
                    toTest.addLast(toAdd);
                    theGod.addLast(toAdd);
                    msg = msg.concat("addLast("+toAdd+")\n") ;
                }
            }
            else {
                Integer rand = StdRandom.uniform(4);
                if(rand == 1){//rand == 1
                    Integer toAdd = StdRandom.uniform(10);//add a random item between 0 to 10
                    toTest.addFirst(toAdd);
                    theGod.addFirst(toAdd);
                    msg = msg.concat("addFirst("+toAdd+")\n") ;
                }
                else if (rand == 0){
                    Integer toAdd = StdRandom.uniform(10);//add a random item between 0 to 10
                    toTest.addLast(toAdd);
                    theGod.addLast(toAdd);
                    msg = msg.concat("addLast("+toAdd+")\n") ;
                }
                else if (rand == 2) {
                    Integer test = toTest.removeLast();
                    Integer god = theGod.removeLast();
                    msg = msg.concat("removeLast()\n") ;
                    assertEquals(msg,god,test);
                }
                else  {
                    Integer test = toTest.removeFirst();
                    Integer god = theGod.removeFirst();
                    msg = msg.concat("removeFirst()\n") ;
                    assertEquals(msg,god,test);
                }
            }
        }
    }
}
