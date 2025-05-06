import React, { useState, useContext } from "react";
import { AccountContext } from "../context/Account";
import { buy } from "../api/api.js";

const Home = () => {
  const {
    balance,
    setBalance,
    holdings,
    setHoldings,
    transactions,
    setTransactions,
    setMessage,
    message
  } = useContext(AccountContext);

  const [amount, setAmount] = useState("");
  const [crypto, setCrypto] = useState("");
  const [errorMessage, setErrorMessage] = useState("");



//=================================================================================================================================
  const handleBuy = async () => {
    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to buy.");
      return;
    }
    try {
      const response = await buy(1, crypto, parseFloat(amount));
      const mess = await response.text(); 

      console.log("Full response:", response);
      console.log("Response body:", mess)

      if (!response.ok) {
        setErrorMessage("Buy failed: " + mess);
        return;
      }

      setMessage(mess);
      setErrorMessage("");
      setAmount("");
      setCrypto("");

    } catch (error) {
      setErrorMessage("Buy error: " + error.message);
    }
  };
//=================================================================================================================================


  const handleSell = () => {
    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to sell.");
      return;
    }
    

    //============================================================================================   
    //call backend to update the current balance - 
      //check if crypto symbol is valid
      //get real price for single amount of the valid crypto 
        //check current amount - update ( holdings + balance + transaction history) or error
    const holding = holdings.find((h) => h.symbol === crypto);
    if (!holding || holding.amount < amount) {
      setErrorMessage("You don't have enough crypto to sell.");
      return;
    }
    const totalPrice = amount * 100; // real price
    setBalance(balance + totalPrice);
    holding.amount -= amount;
    if (holding.amount === 0) {
      setHoldings(holdings.filter((h) => h.symbol !== crypto));
    }
    const newTransaction = {
      type: "Sell",
      symbol: crypto,
      amount: amount,
      price: 100, // real price
      timestamp: new Date().toLocaleString(),
    };
    setTransactions([newTransaction, ...transactions]);
    //============================================================================================



    setErrorMessage(""); // Reset error message
    setAmount(""); // Reset amount
    setCrypto(""); // Reset crypto
  };

  const handleDeposit = () => {
    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to deposit.");
      return;
    }

    //============================================================================================

    //call backend to update the current balance - 
        //check current amount - update ( holdings + balance + transaction history) or error
    setBalance(balance + parseFloat(amount));
    const newTransaction = {
      type: "Deposit",
      amount: amount,
      timestamp: new Date().toLocaleString(),
    };
    setTransactions([newTransaction, ...transactions]);
    //============================================================================================


    setErrorMessage(""); // Reset error message
    setAmount(""); // Reset amount
    setCrypto(""); // Reset crypto
  };


  const handleWithdraw = () => {
    if (amount <= 0 || isNaN(amount)) {
      setErrorMessage("Please enter a valid amount to withdraw.");
      return;
    }


    //============================================================================================
    //call backend to update the current balance - 
        //check current amount - update ( holdings + balance + transaction history) or error
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
    //============================================================================================



    setErrorMessage(""); // Reset error message
    setAmount(""); // Reset amount
    setCrypto(""); // Reset crypto
  };

  return (
    <div className="home">
      <h1 className="text">Crypto Virtual World</h1>
      <h2 className="text">Account Balance: ${balance.toFixed(2)}</h2>
      <div>
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
      </div>
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
