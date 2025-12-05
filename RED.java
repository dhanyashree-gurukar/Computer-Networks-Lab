import java.util.Random;

public class RED {
    public static void main(String[] args){
        int maxPackets = 20;
        int queueSize = 10;
        double maxProbability = 0.7;
        double minProbability = 0.3;
        int queueLength = 0;
        double dropProbability = minProbability;

        Random random = new Random();
        
        for(int i=0; i<maxPackets; i++){
            if(queueLength == queueSize){
                System.out.println("Packets dropped(Queue Full)!!");
                dropProbability = minProbability;
            } else if (random.nextDouble() < dropProbability){
                System.out.println("Packets dropped(Random)!!");
                dropProbability += (maxProbability - minProbability) / (maxPackets - 1);
            } else {
                System.out.println("Packets accepted");
                queueLength++;
                dropProbability = minProbability;
            }
        }
    }
}
