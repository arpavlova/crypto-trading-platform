import React, { createContext, useState } from "react";

export const AccountContext = createContext();

export const AccountProvider = ({ children }) => {
  const INITIAL_BALANCE = 10000;

  const [balance, setBalance] = useState(INITIAL_BALANCE);
  const [holdings, setHoldings] = useState([]); // [{symbol, name, amount}]
  const [transactions, setTransactions] = useState([]); // [{type, symbol, amount, price, timestamp}]

  const resetAccount = () => {
    setBalance(INITIAL_BALANCE);
    setHoldings([]);
    setTransactions([]);
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
        resetAccount,
      }}
    >
      {children}
    </AccountContext.Provider>
  );
};
