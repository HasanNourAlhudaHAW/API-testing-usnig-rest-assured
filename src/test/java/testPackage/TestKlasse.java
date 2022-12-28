package testPackage;

public class TestKlasse {

    void testConflict(int number1, int number2, int param){
        int i = number1;
        int y= number2;
        int result = i+y;

        if(param <= 0){
            System.out.println("Du hast maskhara eingegeben");
        }else{
            result = result - param;
        }

        System.out.println(result);
    }
}
