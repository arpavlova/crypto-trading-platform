import React, { createContext, useState } from "react";

export const AccountContext = createContext();

export const AccountProvider = ({ children }) => {
  const INITIAL_BALANCE = 10000;

  const [balance, setBalance] = useState(INITIAL_BALANCE);
  const [holdings, setHoldings] = useState([]); // [{symbol, name, amount}]
  const [transactions, setTransactions] = useState([]); // [{type, symbol, amount, price, timestamp}]
  const [message, setMessage] = useState("");

  const resetAccount = () => {
    setBalance(INITIAL_BALANCE);
    setHoldings([]);
    setTransactions([]);
  };

  const transactionsHistory = () => {  
    if (transactions.length === 0) {
      setMessage("No transactions yet.");
      return;
    }
    else{
      transactions.map((transaction, index) => (
        <div key={index}>
          <p>
            {transaction.timestamp} | {transaction.type} |{" "}
            {transaction.symbol} | {transaction.amount} units | $
            {(transaction.price * transaction.amount).toFixed(2)}
          </p>
        </div>
      ))
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
        resetAccount,
        transactionsHistory,
      }}
    >
      {children}
    </AccountContext.Provider>
  );
};
