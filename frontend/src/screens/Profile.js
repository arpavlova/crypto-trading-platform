import React, { useContext } from "react";
import { AccountContext } from "../context/Account";

const Profile = () => {
  const { balance, holdings, transactions, resetAccount } = useContext(AccountContext);

  return (
    <div>
      <h2>Account Balance: ${balance.toFixed(2)}</h2>
      <button onClick={resetAccount}>Reset Account</button>
      {/* render holdings and transactions */}
    </div>
  );
};

export default Profile;
