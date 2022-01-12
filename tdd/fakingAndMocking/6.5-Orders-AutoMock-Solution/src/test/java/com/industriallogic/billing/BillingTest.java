package com.industriallogic.billing;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import com.industriallogic.util.Money;

public class BillingTest {

	private static final Money AMOUNT_FOR_WIDGET_A = new Money(540.90);
	private static final Money AMOUNT_FOR_WIDGET_D = new Money(2021.00);
	private static final Money AMOUNT_FOR_WIDGET_G = new Money(22.11);
	private static final Money TOTAL_AMOUNT = AMOUNT_FOR_WIDGET_A.add(AMOUNT_FOR_WIDGET_D).add(AMOUNT_FOR_WIDGET_G);

	private static final String MERCHANT_ID = "Widgets.com";
	private CactusClient mockCactusClient;
	private Billing billing;

	@Before
	public void setUp() {
		mockCactusClient = mock(CactusClient.class);
		billing = new Billing(mockCactusClient);
	}

	@Test
	public void abortIfStartFailed() {
		when(mockCactusClient.startSession(MERCHANT_ID)).thenReturn(false);
		assertFalse(billing.bill(null, null));
	}
	
	@Test
	public void nonsufficientFunds() {
		HolderInfo clientInfo = setupClientInfo();
		TransactionInfo[] transactions = setupTransactionInfo();

		when(mockCactusClient.startSession(MERCHANT_ID)).thenReturn(true);
		when(mockCactusClient.hasSufficientFundsFor(TOTAL_AMOUNT)).thenReturn(false);
		
		assertFalse(billing.bill(clientInfo, transactions));
		
		InOrder sequence = inOrder(mockCactusClient);
		sequence.verify(mockCactusClient).startSession(MERCHANT_ID);
		sequence.verify(mockCactusClient).setHolderInfo(clientInfo);
		sequence.verify(mockCactusClient).hasSufficientFundsFor(TOTAL_AMOUNT);
		sequence.verify(mockCactusClient).stopSession();
	}
	
	@Test
	public void sufficientFunds() {
		HolderInfo clientInfo = setupClientInfo();
		TransactionInfo[] transactions = setupTransactionInfo();

		when(mockCactusClient.startSession(MERCHANT_ID)).thenReturn(true);
		when(mockCactusClient.hasSufficientFundsFor(TOTAL_AMOUNT)).thenReturn(true);
		
		assertTrue(billing.bill(clientInfo, transactions));

		InOrder sequence = inOrder(mockCactusClient);
		sequence.verify(mockCactusClient).startSession(MERCHANT_ID);
		sequence.verify(mockCactusClient).setHolderInfo(clientInfo);
		sequence.verify(mockCactusClient).hasSufficientFundsFor(TOTAL_AMOUNT);
		sequence.verify(mockCactusClient, times(transactions.length)).process(any(TransactionInfo.class));
		sequence.verify(mockCactusClient).stopSession();

	}

	private HolderInfo setupClientInfo() {
		HolderInfo clientInfo = new HolderInfo();
		clientInfo.nameOnCard = "Joe M Smith";
		clientInfo.expiration = "12/09";
		clientInfo.billingAddress = "13 Elm St., NowhereVille, NowhereState";
		return clientInfo;
	}

	private TransactionInfo[] setupTransactionInfo() {
		return new TransactionInfo[] {
			new TransactionInfo(AMOUNT_FOR_WIDGET_A, "Widget A", "20080203"),
			new TransactionInfo(AMOUNT_FOR_WIDGET_D, "Fancy Widget D", "20080206"),
			new TransactionInfo(AMOUNT_FOR_WIDGET_G, "Luxury Widget G", "20080203")
		};
	}
}
