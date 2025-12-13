import random

# -------- CONFIG --------
NUM_CHATTERS = 100
NUM_GROUPS = 100
MEMBERS_PER_GROUP = 50
SIMPLE_MESSAGES = 1000
GROUP_MESSAGES_PER_GROUP = 1000

SYSTEM = "SYSTEM"
random.seed(42)
# ------------------------

# -------- FILES --------
chatter_file = open("chatter.sql", "w")
group_file = open("chat_group.sql", "w")
group_member_file = open("group_member.sql", "w")
simple_chat_file = open("simple_chat.sql", "w")
group_chat_file = open("group_chat.sql", "w")

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

# -------- GROUP MEMBER --------
group_member_id = 1
group_members = {}

w(group_member_file, "-- GROUP MEMBER")
for gid in range(1, NUM_GROUPS + 1):
    members = random.sample(range(1, NUM_CHATTERS + 1), MEMBERS_PER_GROUP)
    group_members[gid] = []

    for idx, chatter_id in enumerate(members):
        access = "ADMIN" if idx == 0 else "MEMBER"

        w(
            group_member_file,
            "INSERT INTO group_member "
            "(chatter_id, group_id, access_level, restricted, created_by) "
            f"VALUES ({chatter_id}, {gid}, '{access}', FALSE, '{SYSTEM}');"
        )

        group_members[gid].append(group_member_id)
        group_member_id += 1

# -------- SIMPLE CHAT --------
w(simple_chat_file, "-- SIMPLE CHAT")
for i in range(1, SIMPLE_MESSAGES + 1):
    sender = random.randint(1, NUM_CHATTERS)
    receiver = random.randint(1, NUM_CHATTERS)

    while receiver == sender:
        receiver = random.randint(1, NUM_CHATTERS)

    w(
        simple_chat_file,
        "INSERT INTO simple_chat "
        "(sender_id, receiver_id, message, read, created_by) "
        f"VALUES ({sender}, {receiver}, "
        f"'Message {i} from {sender} to {receiver}', "
        f"{random.choice(['TRUE', 'FALSE'])}, '{SYSTEM}');"
    )

# -------- GROUP CHAT --------
w(group_chat_file, "-- GROUP CHAT")
for gid, member_ids in group_members.items():
    for msg_no in range(1, GROUP_MESSAGES_PER_GROUP + 1):
        sender_gm_id = random.choice(member_ids)

        w(
            group_chat_file,
            "INSERT INTO group_chat "
            "(group_member_id, message, created_by) "
            f"VALUES ({sender_gm_id}, "
            f"'Group {gid} message {msg_no}', '{SYSTEM}');"
        )

# -------- CLOSE FILES --------
chatter_file.close()
group_file.close()
group_member_file.close()
simple_chat_file.close()
group_chat_file.close()

print("SQL files generated successfully.")
