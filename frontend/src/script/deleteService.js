import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_SERVER_URL;

export const deleteService = async (endpoint) => {
  const response = await axios.delete(`${API_BASE_URL}/${endpoint}`, {
    headers: {
      "Content-Type": "application/json",
    },
  });

  return response.data;
};
