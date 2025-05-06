import React, { useContext } from "react";
import { AccountContext } from "../context/Account.js";
import { reset } from "../api/api.js";

const Profile = () => {

  const { balance, transactionsHistory, userId, setErrorMessage, setBalance, setMessage, message } = useContext(AccountContext);


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
  
      } catch (error) {
        setErrorMessage("Reset error: " + error.message);
      }
    };

  return (
    <div className="profile-container">
      <h2 className="text">Account Balance: ${balance.toFixed(2)}</h2>
      <div className="button-group">
        <button onClick={transactionsHistory}>History</button>
        <button onClick={handleReset}>Reset</button>
      </div>
      {message && <p className="text">{message}</p>}
    </div>
  );
};

export default Profile;
