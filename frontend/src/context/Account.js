import React, { createContext, useState } from "react";

export const AccountContext = createContext();

export const AccountProvider = ({ children }) => {

  const INITIAL_BALANCE = 10000; // temporary
  const userId = 1; // temporary


  const [balance, setBalance] = useState(INITIAL_BALANCE);
  const [holdings, setHoldings] = useState([]);
  const [transactions, setTransactions] = useState([]);
  const [message, setMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

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
        setErrorMessage
      }}
    >
      {children}
    </AccountContext.Provider>
  );
};
