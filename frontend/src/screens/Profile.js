import React, { useContext } from "react";
import { AccountContext } from "../context/Account.js";
import { reset, history, gains } from "../api/api.js";

const Profile = () => {

  const { 
    balance, 
    setTransactions, 
    transactions, 
    userId, 
    setErrorMessage, 
    setBalance, 
    setMessage, 
    message,
    gainLoss,
    setGainLoss
  } = useContext(AccountContext);

  const handleReset = async () => {
      try {
        const response = await reset(userId);
        const data = await response.json();
        if (!response.ok) {
          setErrorMessage("Reset failed: " + data.message);
          return;
        }    
        setBalance(data.balance);
        setMessage(data.message);
        setTransactions([]);
        setGainLoss(null);
  
      } catch (error) {
        setErrorMessage("Reset error: " + error.message);
      }
    };

    const handleHistory = async () => {
      try {
        const data = await history(userId);
        if (!data || data.length === 0) {
          setMessage("No transactions yet.");
          setTransactions([]);
          return;
        }
        const formattedHistory = data.map((transaction) => ({
          timestamp: transaction.dateOfTransaction,
          type: transaction.type,
          symbol: transaction.cryptoSymbol,
          amount: transaction.amount,
          price: transaction.cryptoPrice,
        }));
        setTransactions(formattedHistory);
        setMessage("");
        setGainLoss(null);
      } catch (error) {
        setErrorMessage("Show history error: " + error.message);
      }
    };

    const handleTotalGains = async () => {
      try {
        const data = await gains(userId); // Already returns JSON-parsed double
        setGainLoss(data);
        setTransactions([]);
        setMessage("");
      } catch (error) {
        setErrorMessage("Show gains error: " + error.message);
      }
    };

  return (
    <div className="profile-container">
      <h2 className="text">Account Balance: ${balance.toFixed(2)}</h2>
      <div className="button-group">
        <button onClick={handleHistory}>History</button>
        <button onClick={handleReset}>Reset</button>
        <button onClick={handleTotalGains}>Gains</button>
      </div>
      {message && <p className="text">{message}</p>}
      {gainLoss !== null && (
        <p
        className="text"
        style={{ color: gainLoss >= 0 ? "lightgreen" : "salmon" }}
      >
        Total {gainLoss >= 0 ? "Gain" : "Loss"}: {(gainLoss * 100).toFixed(2)}%
      </p>
      )}
      {transactions.length > 0 && (
      <div className="transaction">
      {transactions.map((transaction, index) => (
        <div key={index}>
          <p>
            {transaction.timestamp} | {transaction.type}
            {transaction.symbol && <> | {transaction.symbol}</>}
            {" "}
            | {transaction.amount} units
            {transaction.price && (
              <> | ${ (transaction.price * transaction.amount).toFixed(2) }</> //if the result is 0 - it should not be displayed
            )}
          </p>
        </div>
      ))}
      </div>
    )}
    </div>
  );
};

export default Profile;
