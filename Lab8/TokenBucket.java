import java.util.Scanner;

public class TokenBucket {
    public static void main(String[] args){
        int tokensGenerated = 0, Rem = 0, remTokens = 0, rate, capacity, n;
    
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of requests: ");
        n = sc.nextInt();
        System.out.print("Enter the bucket capacity: ");
        capacity = sc.nextInt();
        System.out.print("Enter the outgoing rate: ");
        rate = sc.nextInt();
        System.out.println("Enter the number of packets per request: ");

        int requests[] = new int[n];
        boolean flag = false;
        for(int i=0; i<n; i++){
            System.out.print("Packet " + (i+1) + ": ");
            requests[i] = sc.nextInt();
        }

        System.out.printf("Time\tPacket Size\tTokens Generated\t\tStatus\t\tRemaining\n");
        for(int i=0; i<n; i++){
            flag = false;
            tokensGenerated = Math.min(Rem + rate, capacity);
            if(tokensGenerated < 0)
                tokensGenerated = 0;
            if(requests[i] <= tokensGenerated + remTokens){
                Rem = tokensGenerated - requests[i];
                flag = true;
            }
            if(flag){
                remTokens += Rem;
            }
            if(flag){
                System.out.println((i+1) + "\t" + requests[i] + "\t\t" + tokensGenerated + "\t\t\tPacket Transmitted\t\t" +  remTokens);
            }else{
                remTokens += tokensGenerated;
                System.out.println((i+1) + "\t" + requests[i] + "\t\t" + tokensGenerated + "\t\t\tPacket Discarded\t\t" + remTokens);
            }
        }
    }
}
