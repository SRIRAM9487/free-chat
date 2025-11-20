#!/bin/bash

SESSION="BACKEND"

if tmux has-session -t "$SESSION" 2>/dev/null; then
    echo "Session '$SESSION' already running. Attaching..."
else
    tmux new-session -d -s "$SESSION" -c "/home/sriram/git/project/arch-byte/"

    tmux rename-window -t "$SESSION:0" 'MAIN'
    tmux send-keys -t "$SESSION:0" 'nvim .' C-m

    tmux select-window -t "$SESSION:0"

    echo "Dairy backend Session created"
fi


SESSION="DEV"
if tmux has-session -t "$SESSION" 2>/dev/null; then
    echo "Session '$SESSION' already running. Attaching..."
else
    tmux new-session -d -s "$SESSION" -c "/home/sriram/git/project/dairy/dev/"

    tmux rename-window -t "$SESSION:0" 'dev'
    tmux send-keys -t "$SESSION:0" 'nvim .' C-m

    tmux new-window -t "$SESSION:1" -n "server" -c "/home/sriram/git/project/dairy/dev/docker/"
    tmux send-keys -t "$SESSION:1" 'docker-compose -f database.yaml up' C-m

    tmux select-window -t "$SESSION:0"

    echo "Dairy backend Session created"
fi

