#!/bin/bash

 SESSION="E2E"
 if tmux has-session -t "$SESSION" 2>/dev/null; then
     echo "Session '$SESSION' already running. Attaching..."
 else
     tmux new-session -d -s "$SESSION" -c "/home/sriram/git/arch-byte/e2e/"
     tmux rename-window -t "$SESSION:0" 'main'
     tmux new-window -t "$SESSION:1" -n "database" -c "/home/sriram/git/arch-byte/e2e/"
     tmux select-window -t "$SESSION:0"
     echo "E2E Session created"
 fi


SESSION="FRONT"
if tmux has-session -t "$SESSION" 2>/dev/null; then
    echo "Session '$SESSION' already running. Attaching..."
else
    tmux new-session -d -s "$SESSION" -c "/home/sriram/git/arch-byte/frontend/"
    tmux rename-window -t "$SESSION:0" 'code'
    tmux new-window -t "$SESSION:1" -n "server" -c "/home/sriram/git/arch-byte/frontend/"
    tmux select-window -t "$SESSION:0"
    echo "Frontend Session created"
fi

SESSION="BACK"
if tmux has-session -t "$SESSION" 2>/dev/null; then
    echo "Session '$SESSION' already running. Attaching..."
else
    tmux new-session -d -s "$SESSION" -c "/home/sriram/git/arch-byte/backend/"
    tmux rename-window -t "$SESSION:0" 'main'
    tmux new-window -t "$SESSION:1" -n "server" -c "/home/sriram/git/arch-byte/backend/eureka_server/"
    tmux new-window -t "$SESSION:2" -n "admin" -c "/home/sriram/git/arch-byte/backend/admin_server/"
    tmux new-window -t "$SESSION:3" -n "gateway" -c "/home/sriram/git/arch-byte/backend/gateway_server/"
    tmux new-window -t "$SESSION:4" -n "notification" -c "/home/sriram/git/arch-byte/backend/notification_server/"
    tmux new-window -t "$SESSION:5" -n "auth" -c "/home/sriram/git/arch-byte/backend/auth_server/"
    tmux new-window -t "$SESSION:6" -n "chat" -c "/home/sriram/git/arch-byte/backend/chat_server/"
    tmux select-window -t "$SESSION:0"
    echo "Backend Session created"
fi

SESSION="SERVER"
if tmux has-session -t "$SESSION" 2>/dev/null; then
    echo "Session '$SESSION' already running. Attaching..."
else
    tmux new-session -d -s "$SESSION" -c "/home/sriram/git/arch-byte/backend/"
    tmux rename-window -t "$SESSION:0" 'main'
    tmux new-window -t "$SESSION:1" -n "server" -c "/home/sriram/git/arch-byte/backend/eureka_server/"
    tmux new-window -t "$SESSION:2" -n "admin" -c "/home/sriram/git/arch-byte/backend/admin_server/"
    tmux new-window -t "$SESSION:3" -n "gateway" -c "/home/sriram/git/arch-byte/backend/gateway_server/"
    tmux new-window -t "$SESSION:4" -n "notification" -c "/home/sriram/git/arch-byte/backend/notification_server/"
    tmux new-window -t "$SESSION:5" -n "auth" -c "/home/sriram/git/arch-byte/backend/auth_server/"
    tmux new-window -t "$SESSION:6" -n "chat" -c "/home/sriram/git/arch-byte/backend/chat_server/"
    tmux select-window -t "$SESSION:0"
    echo "Backend Server created"
fi


SESSION="DOCKER"

if tmux has-session -t "$SESSION" 2>/dev/null; then
    echo "Session '$SESSION' already running. Attaching..."
else
    tmux new-session -d -s "$SESSION" -c "/home/sriram/git/arch-byte/docker/"
    tmux rename-window -t "$SESSION:0" 'main'
    tmux new-window -t "$SESSION:1" -n "psql" -c "/home/sriram/git/arch-byte/docker/database/"
    tmux new-window -t "$SESSION:2" -n "redis" -c "/home/sriram/git/arch-byte/docker/database/"
    tmux new-window -t "$SESSION:3" -n "prometheus" -c "/home/sriram/git/arch-byte/docker/observability/"
    tmux new-window -t "$SESSION:4" -n "message" -c "/home/sriram/git/arch-byte/docker/message/"
    tmux new-window -t "$SESSION:5" -n "zipkin" -c "/home/sriram/git/arch-byte/docker/zipkin/"
    tmux select-window -t "$SESSION:0"
    echo "DOCKER Session created"
fi

