import java.util.Random;
import java.math.BigInteger;
import java.util.Scanner;

public class RSA {
    public RSA(int bitlength){
        getKeys(bitlength);
    }

    BigInteger publicKey, privateKey, mod;

    public void getKeys(int bitlen){
        Random r = new Random();
        BigInteger p = BigInteger.probablePrime(bitlen, r);
        BigInteger q = BigInteger.probablePrime(bitlen, r);
        mod = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        publicKey = BigInteger.probablePrime(bitlen/2, r);

        while(phi.gcd(publicKey).intValue() != 1){
            publicKey = publicKey.add(BigInteger.ONE);
        }

        privateKey = publicKey.modInverse(phi);
    }

    BigInteger encrypt(BigInteger m){
        return m.modPow(publicKey, mod);
    }

    BigInteger decrypt(BigInteger c){
        return c.modPow(privateKey, mod);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        RSA rsa = new RSA(1024);
        System.out.print("Enter the message to be encrypted: ");
        String m = sc.nextLine();
        BigInteger message = new BigInteger(m.getBytes());
        BigInteger c = rsa.encrypt(message);
        System.out.println("Encrypted message: " + c.longValue());
        BigInteger d = rsa.decrypt(c);
        System.out.println("Decrypted message: " + new String(d.toByteArray()));
    }
}
