import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_SERVER_URL;

export const fetchServiceParam = async (endpoint, params, token) => {
  const response = await axios.get(`${API_BASE_URL}/${endpoint}`, {
    params: params,
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
  });

  return response.data;
};
