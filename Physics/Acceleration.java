package Physics;

import Interface.AccelerationInterface;

public class Acceleration implements AccelerationInterface {
    public Vector3[] accRate;

    public Acceleration(){

    }

    public void initialize(int size){
        accRate = new Vector3[size];
        for(int i = 0; i < size; i++){
            accRate[i] = new Vector3(0,0,0);
        }
    }

    public void add(int i, Vector3 anotherVec){
        accRate[i] = (Vector3) accRate[i].add(anotherVec);
    }

    public Vector3 get(int i){
        return accRate[i];
    }

    @Override
    public String toString() {
        String string = "";
        for(int i = 0; i < accRate.length; i++){
            string += i + " " + accRate[i];
        }
        return string;
    }
}
