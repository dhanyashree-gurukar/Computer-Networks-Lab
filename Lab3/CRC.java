import java.util.Scanner;

public class CRC{
    static String calCRC(String data, String poly, boolean error){
        StringBuffer rem = new StringBuffer(data);
        if(!error){
            for(int i=0; i<poly.length()-1; i++){
                rem.append("0");
            }
        }
        for(int i=0; i<rem.length()-poly.length()+1; i++){
            if(rem.charAt(i) == '1'){
                for(int j=0; j<poly.length(); j++){
                    rem.setCharAt(i+j, (rem.charAt(i+j) == poly.charAt(j)) ? '0' : '1');
                }
            }
        }
        return rem.substring(rem.length()-poly.length()+1);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the data to be sent in bits: ");
        String data = sc.next();
        String poly = "10000100010001010";
        String encoded = data + calCRC(data, poly, false);
        System.out.println("Encoded data: " + encoded);
        System.out.println("Enter the data was received: ");
        String received = sc.next();
        String recString = calCRC(received, poly, true);
        if(Integer.parseInt(recString, 2) == 0)
            System.out.println("Data is correct");
        else
            System.out.println("Data is incorrect!");

        sc.close();
    }
    
}
