// components/Market.js
import React, { useEffect, useState } from "react";
import Axios from "axios";

const Market = () => {
  const [search, setSearch] = useState("");
  const [crypto, setCrypto] = useState([]);

  useEffect(() => {
    Axios.get(
      "https://api.coinstats.app/public/v1/coins?skip=0&limit=100&currency=INR"
    ).then((res) => {
      setCrypto(res.data.coins);
    });
  }, []);

  return (
    <div>
      <h1>Top 20 Cryptocurrencies</h1>
      <input
        type="text"
        placeholder="Search by name..."
        onChange={(e) => setSearch(e.target.value)}
      />
      <table>
        <thead>
          <tr>
            <td>Rank</td>
            <td>Name</td>
            <td>Symbol</td>
            <td>Market Cap</td>
            <td>Price</td>
            <td>Supply</td>
            <td>24h Volume</td>
          </tr>
        </thead>
        <tbody>
          {crypto
            .filter((val) =>
              val.name.toLowerCase().includes(search.toLowerCase())
            )
            .slice(0, 20)
            .map((val, idx) => (
              <tr key={idx}>
                <td>{val.rank}</td>
                <td>
                  <a href={val.websiteUrl} target="_blank" rel="noreferrer">
                    <img src={val.icon} alt={val.name} width="25" />
                  </a>{" "}
                  {val.name}
                </td>
                <td>{val.symbol}</td>
                <td>₹{val.marketCap.toLocaleString()}</td>
                <td>₹{val.price.toFixed(2)}</td>
                <td>{val.availableSupply.toLocaleString()}</td>
                <td>{val.volume.toLocaleString()}</td>
              </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
};

export default Market;
