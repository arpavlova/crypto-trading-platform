const BASE = process.env.REACT_APP_API_BASE_URL;
console.log("API Base URL:", BASE);

// export const getBalance = (userId) =>
//   fetch(`${BASE}/user/${userId}/balance`)
//     .then((res) => res.json());


export const withdraw = (userId, amount) =>
  fetch(`${BASE}/trade/${userId}/withdraw`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ amount }),
  });


export const deposit = (userId, amount) =>
  fetch(`${BASE}/trade/${userId}/deposit`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ amount }),
  });

export const buy = (userId, cryptoSymbol, amount) =>
  fetch(`${BASE}/trade/${userId}/buy`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ cryptoSymbol, amount }),
  }
);

export const sell = (userId, cryptoSymbol, amount) =>
  fetch(`${BASE}/trade/${userId}/sell`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ cryptoSymbol, amount }),
  });

export const history = (userId) =>
  fetch(`${BASE}/user/${userId}/transactions`)
    .then((res) => res.json());

export const gains = (userId) =>
  fetch(`${BASE}/user/${userId}/gains`)
    .then((res) => res.json());

export const reset = (userId) =>
  fetch(`${BASE}/user/${userId}/reset`, {
    method: "POST",
  });