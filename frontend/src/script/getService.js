import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_SERVER_URL;

export const getService = async (endpoint, token) => {
  const response = await axios.get(`${API_BASE_URL}/${endpoint}`, {
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
  });
  return response.data;
};
