import random

# -------- CONFIG --------
NUM_CHATTERS = 100
NUM_GROUPS = 100
CHAT_MESSAGES_PER_GROUP = 300

SYSTEM = "SYSTEM"
random.seed(42)
# ------------------------

# -------- FILES --------
chatter_file = open("V2__chatter.sql", "w")
group_file = open("V3__chat_group.sql", "w")
chat_file = open("V4__chat.sql", "w")

def w(file, line=""):
    file.write(line + "\n")

# -------- CHATTER --------
w(chatter_file, "-- CHATTER")
for i in range(1, NUM_CHATTERS + 1):
    w(
        chatter_file,
        f"INSERT INTO chatter (name, user_id, created_by) "
        f"VALUES ('chatter_{i}', {i}, '{SYSTEM}');"
    )

# -------- CHAT GROUP --------
w(group_file, "-- CHAT GROUP")
for gid in range(1, NUM_GROUPS + 1):
    w(
        group_file,
        f"INSERT INTO chat_group (name, description, created_by) "
        f"VALUES ('group_{gid}', 'Dummy group {gid}', '{SYSTEM}');"
    )

# -------- CHAT (MESSAGES) --------
w(chat_file, "-- CHAT")
for group_id in range(1, NUM_GROUPS + 1):
    for msg_no in range(1, CHAT_MESSAGES_PER_GROUP + 1):
        chatter_id = random.randint(1, NUM_CHATTERS)

        w(
            chat_file,
            "INSERT INTO chat "
            "(chatter_id, group_id, message, created_at) "
            f"VALUES ({chatter_id}, {group_id}, "
            f"'Message {msg_no} in group {group_id}', CURRENT_TIMESTAMP);"
        )

# -------- CLOSE FILES --------
chatter_file.close()
group_file.close()
chat_file.close()

print("SQL files generated successfully for current schema.")
