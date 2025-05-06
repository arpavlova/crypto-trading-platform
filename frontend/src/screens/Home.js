import React, { useState, useContext } from "react";
import { AccountContext } from "../context/Account";
import { buy, sell, deposit, withdraw } from "../api/api.js";

const Home = () => {
  const {
    balance,
    setBalance,
    holdings,
    setHoldings,
    transactions,
    setTransactions,
    setMessage,
    message,
    userId,
    setErrorMessage,
    errorMessage
  } = useContext(AccountContext);

  const [amount, setAmount] = useState("");
  const [crypto, setCrypto] = useState("");

  const handleBuy = async () => {

    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to buy.");
      return;
    }
    try {
      const response = await buy(userId, crypto, parseFloat(amount));
      const data = await response.json();
      if (!response.ok) {
        setErrorMessage("Buy failed: " + data.message);
        return;
      }
  
      setBalance(data.balance);
      setMessage(data.message);
      setErrorMessage("");
      setAmount("");
      setCrypto("");

    } catch (error) {
      setErrorMessage("Buy error: " + error.message);
    }
  };

  const handleSell = async () => {

    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to sell.");
      return;
    }
    try {
      const response = await sell(userId, crypto, parseFloat(amount));
      const data = await response.json();
      if (!response.ok) {
        setErrorMessage("Sell failed: " + data.message);
        return;
      }
      setBalance(data.balance);
      setMessage(data.message);
      setErrorMessage("");
      setAmount("");
      setCrypto("");

    } catch (error) {
      setErrorMessage("Sell error: " + error.message);
    }
  };

  const handleDeposit = async () => {
    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to deposit.");
      return;
    }
    try {
      const response = await deposit(userId, parseFloat(amount));
      const data = await response.json();
      if (!response.ok) {
        setErrorMessage("Deposit failed: " + data.message);
        return;
      }
      setBalance(data.balance);
      setMessage(data.message);
      setErrorMessage("");
      setAmount("");

    } catch (error) {
      setErrorMessage("Deposit error: " + error.message);
    }
  };

  const handleWithdraw = async () => {
    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to withdraw.");
      return;
    }
    try {
      const response = await withdraw(userId, parseFloat(amount));
      const data = await response.json();
      if (!response.ok) {
        setErrorMessage("Withdraw failed: " + data.message);
        return;
      }
      setBalance(data.balance);
      setMessage(data.message);
      setErrorMessage("");
      setAmount("");

    } catch (error) {
      setErrorMessage("Withdraw error: " + error.message);
    }
  };


  return (
    <div className="home">
      <h1 className="text">Crypto Virtual World</h1>
      <h2 className="text">Account Balance: ${balance.toFixed(2)}</h2>
      {/* <div>
        <h3>Your Holdings:</h3>
        {holdings.length === 0 ? (
          <p className="text">No holdings yet.</p>
        ) : (
          holdings.map((holding, index) => (
            <p key={index} className="text">
                {holding.name} ({holding.symbol}): {holding.amount} units
            </p>
          ))
        )}
      </div> */}
      <div className="input-container">
        <h3 className="text">Buy/Sell Crypto</h3>
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

        <div className="button-group">
          <button onClick={handleBuy}>Buy</button>
          <button onClick={handleSell}>Sell</button>
          <button onClick={handleDeposit}>Deposit</button>
          <button onClick={handleWithdraw}>Withdraw</button>
        </div>

        {errorMessage && <p className="text error">{errorMessage}</p>}
        {message && <p className="text">{message}</p>}
      </div>
    </div>
  );
};

export default Home;
