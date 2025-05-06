import React, { useContext } from "react";
import { AccountContext } from "../context/Account.js";

const Profile = () => {

  const { balance, transactionsHistory, resetAccount , message} = useContext(AccountContext);

  return (
    <div className="profile-container">
      <h2 className="text">Account Balance: ${balance.toFixed(2)}</h2>
      <div className="button-group">
        <button onClick={transactionsHistory}>History</button>
        <button onClick={resetAccount}>Reset</button>
      </div>

      {/* {message && <p className="text">{message}</p>} */}
    </div>
  );
};

export default Profile;
