import React, { useEffect, useRef } from "react";

export default function MessageList({ messages, contact }) {
  const endRef = useRef(null);
  const isGroup = contact.type === "group";

  useEffect(() => {
    endRef.current?.scrollIntoView();
  }, [messages]);

  return (
    <section className="flex-1 overflow-auto p-4 bg-[#eff3f6] space-y-6">
      {messages.map((m, index) => {
        const isSequence = messages[index - 1]?.sender === m.sender;

        return (
          <div
            key={m.id}
            className={`flex w-full ${m.fromMe ? "justify-end" : "justify-start"}`}
          >
            <div
              className={`
                relative max-w-[75%] px-4 py-2.5 shadow-sm
                text-[15px] leading-relaxed
                
                ${
                  m.fromMe
                    ? "bg-blue-600 text-white rounded-[18px] rounded-br-sm"
                    : "bg-white text-slate-800 rounded-[18px] rounded-bl-sm"
                }
                
                ${isSequence ? "mt-[-18px]" : ""} 
              `}
            >
              {!m.fromMe && isGroup && !isSequence && (
                <p className="text-[10px] font-bold text-blue-600 mb-1 opacity-90">
                  {m.sender}
                </p>
              )}

              <span className="block">{m.text}</span>

              <div
                className={`text-[9px] font-medium mt-1 text-right
                ${m.fromMe ? "text-blue-100/70" : "text-slate-400"}`}
              >
                {new Date().toLocaleTimeString([], {
                  hour: "2-digit",
                  minute: "2-digit",
                })}
              </div>
            </div>
          </div>
        );
      })}

      <div ref={endRef} />
    </section>
  );
}
