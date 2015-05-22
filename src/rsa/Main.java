package rsa;
import java.math.BigInteger;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		BigInteger firstPrime = BigInteger.probablePrime(512, new Random());
		while (!Rsa.millerRabinPrimeTest(firstPrime)) {
			firstPrime = BigInteger.probablePrime(512, new Random());
		}
		BigInteger secondPrime = BigInteger.probablePrime(512, new Random());
		while (!Rsa.millerRabinPrimeTest(secondPrime)
				|| firstPrime.equals(secondPrime)) {
			secondPrime = BigInteger.probablePrime(512, new Random());
		}

		RsaAttributes rsaAttributes = Rsa
				.calculateKeys(firstPrime, secondPrime);

		// titkos�t�s
		BigInteger m = new BigInteger("123456");
		System.out.println("message: " + m);

		// titkos�t�s gyorshatv�nyoz�ssa: m = �zenet (m^publicKey mod modulus)
		BigInteger c = Rsa.squareAndMultiply(m, rsaAttributes.getPublicKey(),
				rsaAttributes.getModulus());

		System.out.println("secret message: " + c);

		// visszafejt�s gyorshatv�nyoz�ssa: c = titkos �zenet (c^secretKey mod
		// modulus)
		BigInteger originalM = Rsa.squareAndMultiply(c,
				rsaAttributes.getSecretKey(), rsaAttributes.getModulus());

		System.out.println("original message: " + originalM);
	}

}
