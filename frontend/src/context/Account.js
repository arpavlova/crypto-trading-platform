import React, { createContext, useState } from "react";
import { gains } from "../api/api.js";

export const AccountContext = createContext();

export const AccountProvider = ({ children }) => {

  const INITIAL_BALANCE = 10000; // temporary
  const userId = 1; // temporary


  const [balance, setBalance] = useState(INITIAL_BALANCE);
  const [holdings, setHoldings] = useState([]);
  const [transactions, setTransactions] = useState([]);
  const [message, setMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [gainLoss, setGainLoss] = useState(null);

  
  const handleTotalGains = async () => {
    try {

      setTransactions([]);
      setMessage("");
      setHoldings([]);

      const data = await gains(userId);
      // if (!data.ok) {
      //   setErrorMessage("Get gains failed: " + data.message);
      //   return;
      // }  

      setGainLoss(data);

    } catch (error) {
      setErrorMessage("Show gains error: " + error.message);
    }
  };

  return (
    <AccountContext.Provider
      value={{
        balance,
        setBalance,
        holdings,
        setHoldings,
        transactions,
        setTransactions,
        message,
        setMessage,
        userId,
        errorMessage,
        setErrorMessage,
        gainLoss, 
        setGainLoss,
        handleTotalGains
      }}
    >
      {children}
    </AccountContext.Provider>
  );
};
