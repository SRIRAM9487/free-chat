import React, { useState } from "react";
import ChatHeader from "./../ChatHeader";
import MessageList from "./../MessageList";
import MessageInput from "./../MessageInput";
import { all_member } from "../../../../dev/chat/AllMember";
import { messages2 } from "../../../../dev/chat/Message";
import Sidebar from "../sidebar/SideBar";

export default function ChatLayout() {
  const [contacts] = useState(all_member);

  const [activeId, setActiveId] = useState(1);
  const [text, setText] = useState("");

  const [messages, setMessages] = useState(messages2);

  function sendMessage() {
    if (!text.trim()) return;

    const activeContact = contacts.find((c) => c.id === activeId);
    const isGroup = activeContact.type === "group";

    const newMsg = {
      id: Date.now(),
      fromMe: true,
      text,
      ...(isGroup && { sender: "You" }),
    };

    setMessages((prev) => ({
      ...prev,
      [activeId]: [...prev[activeId], newMsg],
    }));

    setText("");
  }

  const activeContact = contacts.find((c) => c.id === activeId);

  return (
    <div className="h-screen flex bg-gray-200">
      <Sidebar
        contacts={contacts}
        activeId={activeId}
        setActiveId={setActiveId}
      />

      <main className="flex-1 flex flex-col">
        <ChatHeader contact={activeContact} />
        <MessageList messages={messages[activeId]} contact={activeContact} />
        <MessageInput text={text} setText={setText} sendMessage={sendMessage} />
      </main>
    </div>
  );
}
