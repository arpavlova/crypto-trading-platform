import React, { useState, useContext } from "react";
import { AccountContext } from "../context/Account";

const Home = () => {
  const {
    balance,
    setBalance,
    holdings,
    setHoldings,
    transactions,
    setTransactions,
  } = useContext(AccountContext);

  const [amount, setAmount] = useState("");
  const [crypto, setCrypto] = useState(""); // symbol of crypto for buy/sell
  const [errorMessage, setErrorMessage] = useState("");

  // Handle Buy Operation
  const handleBuy = () => {
    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to buy.");
      return;
    }
    const totalPrice = amount * 100; // Assume a static price for now
    if (totalPrice > balance) {
      setErrorMessage("Insufficient funds for the purchase.");
      return;
    }
    
    // Update balance
    setBalance(balance - totalPrice);

    // Update holdings
    const existingHolding = holdings.find((h) => h.symbol === crypto);
    if (existingHolding) {
      existingHolding.amount += amount;
    } else {
      setHoldings([
        ...holdings,
        { symbol: crypto, name: crypto.toUpperCase(), amount: parseFloat(amount) },
      ]);
    }

    // Record transaction
    const newTransaction = {
      type: "Buy",
      symbol: crypto,
      amount: amount,
      price: 100, // Assume price of 100 for now
      timestamp: new Date().toLocaleString(),
    };
    setTransactions([newTransaction, ...transactions]);

    setErrorMessage(""); // Reset error message
    setAmount(""); // Reset amount
  };

  // Handle Sell Operation
  const handleSell = () => {
    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to sell.");
      return;
    }
    const holding = holdings.find((h) => h.symbol === crypto);
    if (!holding || holding.amount < amount) {
      setErrorMessage("You don't have enough crypto to sell.");
      return;
    }

    // Update balance
    const totalPrice = amount * 100; // Assume a static price for now
    setBalance(balance + totalPrice);

    // Update holdings
    holding.amount -= amount;
    if (holding.amount === 0) {
      setHoldings(holdings.filter((h) => h.symbol !== crypto));
    }

    // Record transaction
    const newTransaction = {
      type: "Sell",
      symbol: crypto,
      amount: amount,
      price: 100, // Assume price of 100 for now
      timestamp: new Date().toLocaleString(),
    };
    setTransactions([newTransaction, ...transactions]);

    setErrorMessage(""); // Reset error message
    setAmount(""); // Reset amount
  };

  // Handle Deposit
  const handleDeposit = () => {
    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to deposit.");
      return;
    }
    setBalance(balance + parseFloat(amount));
    const newTransaction = {
      type: "Deposit",
      amount: amount,
      timestamp: new Date().toLocaleString(),
    };
    setTransactions([newTransaction, ...transactions]);

    setErrorMessage(""); // Reset error message
    setAmount(""); // Reset amount
  };

  // Handle Withdrawal
  const handleWithdraw = () => {
    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to withdraw.");
      return;
    }
    if (amount > balance) {
      setErrorMessage("Insufficient funds to withdraw.");
      return;
    }
    setBalance(balance - parseFloat(amount));
    const newTransaction = {
      type: "Withdraw",
      amount: amount,
      timestamp: new Date().toLocaleString(),
    };
    setTransactions([newTransaction, ...transactions]);

    setErrorMessage(""); // Reset error message
    setAmount(""); // Reset amount
  };

  return (
    <div className="home">
      <h1>Virtual Crypto World</h1>

      <h2>Account Balance: ${balance.toFixed(2)}</h2>

      <div>
        <h3>Your Holdings:</h3>
        {holdings.length === 0 ? (
          <p>No holdings yet.</p>
        ) : (
          holdings.map((holding, index) => (
            <div key={index}>
              <p>
                {holding.name} ({holding.symbol}): {holding.amount} units
              </p>
            </div>
          ))
        )}
      </div>

      { /*<div>
        <h3>Transaction History:</h3>
        {transactions.length === 0 ? (
          <p>No transactions yet.</p>
        ) : (
          transactions.map((transaction, index) => (
            <div key={index}>
              <p>
                {transaction.timestamp} | {transaction.type} |{" "}
                {transaction.symbol} | {transaction.amount} units | $
                {(transaction.price * transaction.amount).toFixed(2)}
              </p>
            </div>
          ))
        )}
      </div> */}

      <div className="input-container">
        <h3>Buy/Sell Crypto</h3>
        <input
          type="text"
          placeholder="Enter cryptocurrency symbol (e.g., BTC)"
          onChange={(e) => setCrypto(e.target.value.toUpperCase())}
          value={crypto}
        />
        <input
          type="number"
          placeholder="Enter amount"
          onChange={(e) => setAmount(e.target.value)}
          value={amount}
        />

        <div>
          <button onClick={handleBuy}>Buy</button>
          <button onClick={handleSell}>Sell</button>
          <button onClick={handleDeposit}>Deposit</button>
          <button onClick={handleWithdraw}>Withdraw</button>
        </div>

        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
      </div>
    </div>
  );
};

export default Home;
