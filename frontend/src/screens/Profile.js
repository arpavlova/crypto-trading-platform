// src/screens/Profile.js
import React, { useContext } from "react";
import { AccountContext } from "../context/Account";

const Profile = () => { 
  const {
    balance,
    holdings,
    transactions,
    resetAccount,
  } = useContext(AccountContext);

  return (
    <div style={{ padding: "20px" }}>
      <h2>Account Summary</h2>
      <p><strong>Balance:</strong> ${balance.toFixed(2)}</p>

      <h3>Holdings:</h3>
      {Object.keys(holdings).length === 0 ? (
        <p>No crypto held.</p>
      ) : (
        <ul>
          {Object.entries(holdings).map(([symbol, amount]) => (
            <li key={symbol}>
              {symbol}: {amount}
            </li>
          ))}
        </ul>
      )}

      <h3>Transactions:</h3>
      {transactions.length === 0 ? (
        <p>No transactions yet.</p>
      ) : (
        <ul>
          {transactions.map((tx, index) => (
            <li key={index}>
              [{tx.type.toUpperCase()}] {tx.amount} {tx.symbol} at ${tx.price.toFixed(2)} each
            </li>
          ))}
        </ul>
      )}

      <button onClick={resetAccount} style={{ marginTop: "20px", padding: "10px 20px" }}>
        Reset Account
      </button>
    </div>
  );
};

export default Profile;
