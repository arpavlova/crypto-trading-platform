import React, { useContext, useEffect } from "react";
import { AccountContext } from "../context/Account.js";
import { reset, history, holding } from "../api/api.js";

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
    setGainLoss,
    handleTotalGains,
    setHoldings,
    holdings
  } = useContext(AccountContext);

  useEffect(() => {
    return () => {
      setTransactions([]);
      setMessage("");
      setGainLoss(null);
      setHoldings([]);
    };
  }, []);

  const handleReset = async () => {
      try {

        setHoldings([]);
        setTransactions([]);
        setGainLoss(null);

        const response = await reset(userId);
        const data = await response.json();

        if (!response.ok) {
          setErrorMessage("Reset failed: " + data.message);
          return;
        }    
        setBalance(data.balance);
        setMessage(data.message);
 
  
      } catch (error) {
        setErrorMessage("Reset error: " + error.message);
      }
    };

    const handleHistory = async () => {
      try {

        setTransactions([]);
        setMessage("");
        setGainLoss(null);
        setHoldings([]);

        const data = await history(userId);
        if (!data || data.length === 0) {
          setMessage("No transactions yet.");
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
 
      } catch (error) {
        setErrorMessage("Show history error: " + error.message);
      }
    };

    const handleHoldings = async () => {
      try {

        setTransactions([]);
        setMessage("");
        setGainLoss(null);

        const data = await holding(userId);

        if (!data || data.length === 0) {
          setMessage("No holdings yet.");

          return;
        }
        const formattedHoldings = data.map((holding) => ({
          symbol: holding.cryptoSymbol,
          amount: holding.amount,
          price: holding.price,
        }));

        setHoldings(formattedHoldings);

      } catch (error) {
        setErrorMessage("Show history error: " + error.message);
      }
    };

  return (
    <div className="profile-container">
      <h2 className="text">Account Balance: ${balance.toFixed(2)}</h2>
      <div className="button-group">
        <button onClick={handleHistory}>History</button>
        <button onClick={handleReset}>Reset</button>
        <button onClick={handleTotalGains}>Gains</button>
        <button onClick={handleHoldings}>Holdings</button>
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
    {holdings.length > 0 && (
      <div className="transaction">
      {holdings.map((curr, index) => (
        <div key={index}>
          <p>
          {curr.amount} | {curr.symbol} | ${ (curr.price).toFixed(2) }
          </p>
        </div>
      ))}
      </div>
    )}
    </div>
  );
};


//todo
//to include the current price

export default Profile;
