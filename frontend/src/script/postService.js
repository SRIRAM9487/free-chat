import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_SERVER_URL;

export const postService = async (endpoint, data, token) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/${endpoint}`, data, {
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    });
    return response.data;
  } catch (error) {
    throw error.response.data;
  }
};
