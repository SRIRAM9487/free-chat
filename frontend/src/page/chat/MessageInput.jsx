import React from "react";
import { FiSend } from "react-icons/fi";
import InputField from "../../component/InputField";
import { Button } from "antd";

export default function MessageInput({ text, setText, sendMessage }) {
  return (
    <footer className="p-3 bg-white flex items-center gap-2">
      <InputField
        placeholder="Message..."
        value={text}
        onChange={(e) => setText(e.target.value)}
        className=""
        size="middle"
      />

      <Button
        color="blue"
        variant="solid"
        onClick={sendMessage}
        className="px-4 py-2 flex items-center gap-2 rounded-md"
      >
        <FiSend />
        <span>Send</span>
      </Button>
    </footer>
  );
}
