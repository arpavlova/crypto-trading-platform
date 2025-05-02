import React, { useContext } from "react";
import { AccountContext } from "../context/Account";

const Profile = () => {
  const { balance, holdings, transactions, resetAccount } = useContext(AccountContext);

  return (
    <div>
      <h2>Account Balance: ${balance.toFixed(2)}</h2>
      <div>
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
      </div>
      <button onClick={resetAccount}>Reset Account</button>
      {/* render holdings and transactions */}
    </div>
  );
};

export default Profile;
