package dev.se133.project.member.wallet;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class WalletTest {
	Wallet wallet;
	
	@Before
	public void setUpBefore() {
		wallet = new Wallet();
	}

	@Test
	public void testToString() {
		System.out.println(wallet);
		
		System.out.println(wallet.getPoints().addToCount(1400));
		System.out.println(wallet.getCash().addToCount(12000));
		
		System.out.println(wallet);
	}
}
