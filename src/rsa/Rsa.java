package rsa;
import java.math.BigInteger;

public class Rsa {

	// a
	private static final BigInteger publicKey = new BigInteger("65537");

	public static RsaAttributes calculateKeys(BigInteger firstPrime,
			BigInteger secondPrime) {

		RsaAttributes attributes = new RsaAttributes();

		attributes.setModulus(firstPrime.multiply(secondPrime));
		attributes.setPublicKey(publicKey);

		BigInteger phiN = (firstPrime.subtract(BigInteger.ONE))
				.multiply((secondPrime.subtract(BigInteger.ONE)));

		attributes.setSecretKey(extendedEuclidean(phiN, publicKey));

		return attributes;
	}

	// prime = n a fuzetben
	public static boolean millerRabinPrimeTest(BigInteger prime) {
		int k = 0;
		BigInteger a = new BigInteger("2");
		BigInteger nMinusOne = prime.subtract(new BigInteger("1"));
		BigInteger m = new BigInteger(nMinusOne.toString());
		while ((m.remainder(new BigInteger("2"))).equals(BigInteger.ZERO)) {
			k++;
			m = m.divide(new BigInteger("2"));
		}

		BigInteger b = squareAndMultiply(a, m, prime);

		if (b.equals(BigInteger.ONE)) {
			return true;
		}
		for (int i = 0; i < k - 1; i++) {
			if (b.equals(nMinusOne)) {
				return true;
			}
			b = squareAndMultiply(b, new BigInteger("2"), prime);
		}
		if (b.equals(nMinusOne)) {
			return true;
		}

		return false;
	}

	// gyorshatványozás
	public static BigInteger squareAndMultiply(BigInteger number,
			BigInteger square, BigInteger mod) {

		// bináris hatvány
		String squareBinary = createBinaryform(square);

		// képlet alapján (füzet): result^2 * alapszám^0vagy1
		BigInteger result = new BigInteger("1");
		for (int i = 0; i < squareBinary.length(); i++) {
			result = (result.pow(2)).multiply(number.pow(Character
					.getNumericValue(squareBinary.charAt(i))));
			result = result.remainder(mod);
		}

		return result;

	}

	// bináris string a decimális számból (hatványból)
	public static String createBinaryform(BigInteger number) {

		String result = "";
		BigInteger remainder = null;
		// maradékos osztással számoljuk a binárist
		while (!(number.equals(BigInteger.ONE))) {
			remainder = number.remainder(new BigInteger("2"));
			result += remainder;
			number = number.shiftRight(1);
		}
		result += "1";
		return new StringBuilder(result).reverse().toString();
	}

	public static BigInteger extendedEuclidean(BigInteger phiN,
			BigInteger publicKey) {

		BigInteger divident = phiN;
		BigInteger divisor = publicKey;
		BigInteger quotient = BigInteger.ZERO;
		BigInteger yNull = BigInteger.ZERO;
		BigInteger yOne = BigInteger.ONE;

		int counter;
		for (counter = 1; !divisor.equals(BigInteger.ZERO); counter++) {
			if (counter != 1) {
				BigInteger tempY = yOne;
				yOne = yOne.multiply(quotient).add(yNull);
				yNull = tempY;
			}
			quotient = divident.divide(divisor);
			BigInteger tempNextDivident = divisor;
			divisor = divident.remainder(divisor);
			divident = tempNextDivident;
		}

		BigInteger secretKey = yOne.multiply(new BigInteger("-1").pow(counter));
		if (secretKey.signum() < 0) {
			secretKey = secretKey.add(phiN);
		}

		return secretKey;
	}
}
